package com.sparta.delivery.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * [역할]
 * - 로그인 요청 바디를 표현하는 입력 DTO(record).
 * - 컨트롤러에서 @Valid로 검증되어 서비스로 전달됨.
 *
 * [주요 기능]
 * - 이메일/비밀번호의 필수성 및 형식/길이 검증
 * - 민감정보(비밀번호)는 로깅 금지 전제(로그 남길 때 주의)
 *
 * [사용 위치]
 * - AuthController.login(@Valid @RequestBody LoginRequestDto req)
 */
public record LoginRequestDto(

        /** 로그인 아이디로 사용하는 이메일(필수, 형식 검사) */
        @NotBlank(message = "이메일 입력은 필수 입력값입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        @Size(max = 100, message = "이메일은 최대 100자까지 입력 가능합니다.")
        String email,

        /** 로그인 비밀번호(필수, 최소 8자 권장) */
        @NotBlank(message = "비밀번호 입력은 필수 입력값입니다.")
        @Size(min = 8, max = 64, message = "비밀번호는 8~64자여야 합니다.")
        String password
) {}
