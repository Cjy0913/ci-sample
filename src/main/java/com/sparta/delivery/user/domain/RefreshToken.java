package com.sparta.delivery.user.domain;

import com.sparta.delivery.global.unit.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * [역할]
 * - JWT 인증 시스템에서 사용되는 Refresh Token 정보를 저장하는 엔티티.
 * - 각 사용자(User)와 1:N(다대일) 관계로 연결되어, 하나의 유저가 여러 토큰을 가질 수 있음.
 *
 * [주요 기능]
 * - refreshToken : 실제 리프레시 토큰 문자열
 * - user         : 토큰 소유자 (User 엔티티 참조)
 * - exp          : 토큰 만료 일시 (DB에서 만료 여부 확인용)
 *
 * [설계 포인트]
 * - BaseEntity 상속 → createdAt, updatedAt 자동 기록
 * - @ManyToOne(fetch = FetchType.LAZY) → 필요 시점에만 User 조회 (지연 로딩)
 * - @Temporal(TemporalType.TIMESTAMP) → Date 필드(exp)를 DATETIME 타입으로 매핑
 *
 * [사용 위치]
 * - RefreshTokenRepository
 * - AuthService / UserService (로그인 시 저장, 로그아웃 시 삭제)
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class
RefreshToken extends BaseEntity {

    /** PK: AUTO_INCREMENT */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 실제 리프레시 토큰 문자열 (JWT 형식) */
    @Column(nullable = false, unique = true, length = 300)
    private String refreshToken;

    /**
     * [설명]
     * - 단방향 매핑으로 User 엔티티와 연결.
     * - 하나의 유저는 여러 RefreshToken을 가질 수 있다.
     * - fetch = LAZY → 필요할 때만 User를 조회하여 성능 최적화.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /** 토큰 만료 일시 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date exp;

    /**
     * [생성자 - Builder 패턴]
     * - 토큰 문자열, 소유자, 만료일을 받아 RefreshToken 객체 생성
     */
    @Builder
    public RefreshToken(String refreshToken, User user, Date exp) {
        this.refreshToken = refreshToken;
        this.user = user;
        this.exp = exp;
    }
}
