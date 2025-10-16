package com.sparta.delivery.user.controller;

import com.sparta.delivery.global.unit.common.BaseResponse;
import com.sparta.delivery.global.unit.common.BaseStatus;
import com.sparta.delivery.user.dto.LoginRequestDto;
import com.sparta.delivery.user.dto.SignUpRequestDto;
import com.sparta.delivery.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * [역할]
 * - 사용자 관련 요청(회원가입, 로그인 등)을 처리하는 REST 컨트롤러.
 * - 클라이언트 요청을 받아 Service 계층으로 위임하고, 표준 응답 형식(BaseResponse)으로 결과를 반환한다.
 *
 * [주요 기능]
 * - POST /api/user/signup : 회원가입 요청 처리
 * - POST /api/user/login  : 로그인 요청 처리 (JWT 발급 포함)
 * - GET  /api/user/test   : 인증 테스트용 엔드포인트
 *
 * [사용 기술]
 * - @Valid : DTO 유효성 검증
 * - @RestController : JSON 기반 REST API 처리
 * - BaseResponse : 공통 응답 래퍼 (성공/실패 응답 일관화)
 *
 * [사용 위치]
 * - 프론트엔드의 회원가입 / 로그인 API 호출 시
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService; // 비즈니스 로직 담당 서비스

    /**
     * [기능]
     * - 스프링 시큐리티 인증 필터 작동 여부 테스트용
     * - 인증 성공 시 "login test" 문자열 반환
     *
     * @return 인증 성공 시 단순 문자열
     */
    @GetMapping("/test")
    public String loginTest() {
        return "login test";
    }

    /**
     * [기능]
     * - 회원가입 요청을 처리한다.
     * - SignUpRequestDto에 담긴 회원정보를 바탕으로 새로운 User 생성
     * - BaseResponse.ok(BaseStatus.CREATED)를 반환하여 201 상태 코드 응답
     *
     * @param signUpRequestDto 회원가입 요청 DTO
     * @return BaseResponse<Void> (공통 응답)
     */
    @PostMapping("/signup")
    public BaseResponse<Void> signup(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        userService.signup(signUpRequestDto);
        return BaseResponse.ok(BaseStatus.CREATED);
    }

    /**
     * [기능]
     * - 로그인 요청을 처리한다.
     * - LoginRequestDto(email, password)를 검증하고 인증 성공 시 JWT 토큰을 응답 헤더에 추가
     * - BaseResponse.ok(BaseStatus.OK)로 200 상태 코드 반환
     *
     * @param loginRequest 로그인 요청 DTO
     * @param response     HTTP 응답 객체 (JWT 토큰을 헤더로 추가할 때 사용)
     * @return BaseResponse<Void>
     */
    @PostMapping("/login")
    public BaseResponse<Void> login(@RequestBody @Valid LoginRequestDto loginRequest, HttpServletResponse response) {
        userService.login(loginRequest, response);
        return BaseResponse.ok(BaseStatus.OK);
    }
}
