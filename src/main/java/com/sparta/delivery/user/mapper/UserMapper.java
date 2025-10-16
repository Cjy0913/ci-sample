package com.sparta.delivery.user.mapper;

import com.sparta.delivery.user.domain.User;
import com.sparta.delivery.user.dto.SignUpRequestDto;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * [역할]
 * - 회원가입 요청 DTO(SignUpRequestDto)를 JPA Entity(User)로 변환하는 매퍼 클래스.
 * - DTO → Entity 변환 로직을 한 곳에 모아 서비스 코드의 가독성과 유지보수성을 높인다.
 *
 * [주요 기능]
 * - 비밀번호 암호화(PasswordEncoder)를 수행한 후 User Entity로 변환
 * - 기본 권한(Role.CUSTOMER)은 User 엔티티 내부 Builder에서 자동 설정됨
 *
 * [사용 위치]
 * - UserService.signup() 내부
 *
 * 예시)
 *   User user = UserMapper.toUser(requestDto, passwordEncoder);
 *   userRepository.save(user);
 */
public class UserMapper {

    /**
     * [기능]
     * - 회원가입 요청 DTO를 기반으로 User 엔티티 생성
     * - 비밀번호는 PasswordEncoder를 이용해 암호화 처리
     *
     * @param dto 회원가입 요청 데이터
     * @param passwordEncoder 비밀번호 암호화용 인코더 (BCrypt 등)
     * @return User 엔티티 객체
     */
    public static User toUser(SignUpRequestDto dto, PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))  // 🔐 비밀번호 암호화
                .nickname(dto.nickname())
                .phoneNumber(dto.phoneNumber())
                .build();
    }
}
