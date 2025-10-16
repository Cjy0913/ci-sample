package com.sparta.delivery.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * [역할]
 * - 회원가입 시 클라이언트로부터 전달받는 요청 데이터를 담는 DTO(record).
 * - Controller에서 @Valid로 검증된 후 Service 계층으로 전달된다.
 *
 * [주요 기능]
 * - 이메일/비밀번호/닉네임/전화번호의 필수 입력 검증
 * - 이메일은 정규식 패턴을 통한 형식 검사
 * - 비밀번호는 기본 길이 제한(8~64자)을 통해 보안 강화 가능
 *
 * [사용 위치]
 * - AuthController.signup(@Valid @RequestBody SignUpRequestDto req)
 */
public record SignUpRequestDto(

        /** 이메일 (고유값, 형식검사 포함) */
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "이메일 형식이 올바르지 않습니다."
        )
        @NotBlank(message = "이메일 입력은 필수 입력값입니다.")
        String email,

        /** 비밀번호 (암호화 대상, 필수 입력) */
        @NotBlank(message = "비밀번호 입력은 필수 입력값입니다.")
        @Size(min = 8, max = 64, message = "비밀번호는 8~64자여야 합니다.")
        String password,

        /** 사용자 닉네임 (표시용 이름) */
        @NotBlank(message = "닉네임은 필수 입력값입니다.")
        String nickname,

        /** 전화번호 (연락처, 숫자/하이픈 허용) */
        @NotBlank(message = "전화번호는 필수 입력값입니다.")
        @Pattern(
                regexp = "^[0-9\\-]{9,20}$",
                message = "전화번호 형식이 올바르지 않습니다."
        )
        String phoneNumber
) {}
