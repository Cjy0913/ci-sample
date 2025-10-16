package com.sparta.delivery.user.service;

import com.sparta.delivery.global.exception.BusinessException;
import com.sparta.delivery.global.exception.domain.ErrorCode;
import com.sparta.delivery.global.unit.utils.CookieUtils;
<<<<<<< Updated upstream
import com.sparta.delivery.security.JwtUtil;
import com.sparta.delivery.user.domain.RefreshToken;
import com.sparta.delivery.user.domain.User;
import com.sparta.delivery.user.dto.LoginRequestDto;
import com.sparta.delivery.user.dto.RefreshTokenDto;
import com.sparta.delivery.user.dto.SignUpRequestDto;
=======
import com.sparta.delivery.user.domain.RefreshToken;
import com.sparta.delivery.user.domain.User;
import com.sparta.delivery.user.dto.SinUpRequestDto;
import com.sparta.delivery.user.mapper.UserMapper;
>>>>>>> Stashed changes
import com.sparta.delivery.user.repository.RefreshTokenRepository;
import com.sparta.delivery.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
<<<<<<< Updated upstream
import java.time.Instant;
import java.util.Date;
=======
>>>>>>> Stashed changes
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
<<<<<<< Updated upstream
    private final JwtUtil jwtUtil;
=======
>>>>>>> Stashed changes


    //암호화 후 db에 회원가입 정보 저장
    @Transactional
<<<<<<< Updated upstream
    public void signup(SignUpRequestDto RequestDto) {
        if (userRepository.findByEmail(RequestDto.email()).isPresent()) { // 이메일 중복
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);  //409
        }

//        // 확장성 생각하면 유저 엔티티 생성 추적이 힘들어질 가능성이 높아서, builder 말고 파라미터 많아도 정적 팩토리 메서드나 생성자로 하는게 나을 수 있다
        User user = new User(
                RequestDto.email(),
                passwordEncoder.encode(RequestDto.password()),
                RequestDto.nickname(),
                RequestDto.phoneNumber()
        );

=======
    public void signup(SinUpRequestDto singUpRequest) {
        if (userRepository.findByEmail(singUpRequest.email()).isPresent()) {
            throw new BusinessException(EMAIL_ALREADY_EXISTS);  //409
        }
        User user = UserMapper.toUser(singUpRequest, passwordEncoder);
>>>>>>> Stashed changes
        userRepository.save(user);
    }


    //로그인
<<<<<<< Updated upstream
    @Transactional
    public void login(LoginRequestDto dto, HttpServletResponse response) {
=======
    @Transactional(readOnly = true)
    public void login(LoginRequest dto, HttpServletResponse response) {
>>>>>>> Stashed changes

        //가입된 email과 password가 같은지 확인
        Optional<User> findUser = userRepository.findByEmail(dto.email());

        if (findUser.isEmpty()) {  //이메일이 존재하지 않다 반환 시 찾을 때까지 이메일 무한 입력 가능성이 있으니 404 반환
            throw new BusinessException(ErrorCode.LOGIN_USER_NOT_FOUND); //404
        }

        User user = findUser.get();

<<<<<<< Updated upstream
        // 입력된 비밀번호, 저장된 비밀번호 비교
=======
>>>>>>> Stashed changes
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new BusinessException(ErrorCode.LOGIN_USER_NOT_FOUND);  //404
        }

<<<<<<< Updated upstream
        //TODO 이부분 코드 검증이랑 다 추가해야 합니다

        issueAndSetAccessToken(response, user.getEmail());
        issueAndSetRefreshToken(response, user);


    }

    public void issueAndSetAccessToken(HttpServletResponse response, String email) {

        String accessToken = jwtUtil.issueAccessToken(email);   // accessToken 발급
        response.setHeader("Authorization", accessToken); // accessToken은 헤더에 저장
    }

    public void issueAndSetRefreshToken(HttpServletResponse response, User user) {

        refreshTokenRepository.deleteByUser(user); // db에 리프레시 토큰 있으면 삭제

        // 만료 시간도 받아오기 위해 Dto로 전달
        RefreshTokenDto refreshTokenDto = jwtUtil.issueRefreshToken(user.getEmail());
        String refreshToken = refreshTokenDto.token();
        Date exp = refreshTokenDto.exp();

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .refreshToken(refreshToken)
                        .user(user)
                        .exp(exp)
                        .build()
        );

        Duration ttlTime = Duration.between(
                Instant.now(),
                exp.toInstant()
        );

        // refreshToken은 http only 쿠키 방식으로 클라이언트에게 줌, ttlTime만큼 시간이 경과하면 삭제됨 ->이러면 db나 토큰에 만료 시간 설정 없어도 되나
        CookieUtils.setRefreshTokenCookie(response, refreshToken, ttlTime);
    }


}
=======
        //가입된 정보가 일치하고 db에 refreshToken이 DB에 존재하고 있어야 하고 만약 기간 만료 시 재발급

        // DB 조회
        Optional<RefreshToken> dbToken = refreshTokenRepository.findRefreshTokenByUser(user);

        // DB에도 없거나 만료시 새로 발급
        refreshToken = jwtTokenProvider.issueRefreshToken(user.getId(), user.getRole(), user.getEmail());
        dbToken.ifPresent(refreshTokenRepository::delete);
        refreshTokenRepository.save(RefreshToken.builder().refreshToken(refreshToken).user(user).build());


        //String accessToken = jwtTokenProvider.issueAccessToken(user.getId(), user.getRole(), user.getEmail());

        Duration ttlTime = ttlTime(refreshToken);

        // http only 쿠키 방식으로 refresh Token을 클라이언트에게 줌
        response.setHeader("Authorization", "Bearer " + accessToken);
        CookieUtils.setRefreshTokenCookie(response, refreshToken, ttlTime);

    }




}




>>>>>>> Stashed changes
