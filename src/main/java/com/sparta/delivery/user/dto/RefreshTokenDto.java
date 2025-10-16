package com.sparta.delivery.user.dto;

import java.util.Date;

/**
 * [역할]
 * - 리프레시 토큰(Refresh Token)을 표현하는 응답용 DTO(record).
 * - 클라이언트가 새 Access Token을 요청할 때, 서버가 토큰 만료 정보를 함께 반환할 수 있도록 함.
 *
 * [주요 기능]
 * - token : 실제 리프레시 토큰 문자열
 * - exp   : 토큰의 만료 시간(Unix timestamp나 Date 객체 형태)
 *
 * [사용 위치]
 * - AuthController.refreshToken() 또는 로그인 응답(UserLoginResponse)에 포함되어 사용됨.
 * - JWT 기반 인증 시스템에서 Access Token 재발급 시 활용.
 */
public record RefreshTokenDto(
        /** 리프레시 토큰 문자열 (JWT 형식 등) */
        String token,

        /** 토큰 만료 시각 (Date 타입, 클라이언트에서 비교 가능) */
        Date exp
) {}
