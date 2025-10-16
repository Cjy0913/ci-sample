package com.sparta.delivery.user.repository;

import com.sparta.delivery.user.domain.RefreshToken;
import com.sparta.delivery.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * [역할]
 * - RefreshToken 엔티티에 대한 데이터베이스 접근을 담당하는 JPA Repository 인터페이스.
 * - 사용자별 리프레시 토큰의 저장, 조회, 삭제 기능을 제공한다.
 *
 * [주요 기능]
 * - findRefreshTokenByUser(User user): 특정 사용자의 리프레시 토큰 조회
 * - deleteByUser(User user): 특정 사용자의 리프레시 토큰 삭제 (재로그인 시 이전 토큰 무효화)
 *
 * [사용 위치]
 * - AuthService.refreshToken(), AuthService.login(), AuthService.logout()
 *
 * [참고]
 * - JpaRepository<RefreshToken, Long> 을 상속하므로 기본 CRUD(save, findById, delete 등) 자동 지원.
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    /**
     * [기능]
     * - 특정 사용자(User)에 해당하는 리프레시 토큰을 조회한다.
     *
     * @param user 토큰 소유자
     * @return Optional<RefreshToken>
     */
    Optional<RefreshToken> findRefreshTokenByUser(User user);

    /**
     * [기능]
     * - 특정 사용자(User)의 리프레시 토큰을 삭제한다.
     *   (로그아웃 시 또는 새로운 로그인 시 이전 토큰을 무효화하는 용도)
     *
     * @param user 토큰 소유자
     */
    void deleteByUser(User user);
}
