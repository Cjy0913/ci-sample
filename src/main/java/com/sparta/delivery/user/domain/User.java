package com.sparta.delivery.user.domain;

import com.sparta.delivery.global.unit.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [역할]
 * - p_user 테이블과 매핑되는 사용자 엔티티 (JPA 영속 대상)
 * - 계정/권한/프로필의 "상태"를 보유하고, 의미 있는 상태 변경 메서드를 제공
 *
 * [주요 기능]
 * - PK 자동 증가(Long, IDENTITY)로 사용자 식별
 * - 이메일 고유성(Unique) 보장
 * - 도메인 메서드(updateNickname, updatePhoneNumber, updateRole)로 상태 캡슐화
 * - 기본 권한을 CUSTOMER로 초기화
 * - BaseEntity 상속으로 생성/수정 시각 등 공통 필드 자동 관리
 */
@Entity
@Table(name = "p_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    /** PK: AUTO_INCREMENT (user_id) */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 로그인 식별용 이메일 (고유) */
    @Column(length = 40, nullable = false, unique = true)
    private String email;

    /** BCrypt 등으로 해시된 비밀번호 */
    @Column(length = 100, nullable = false)
    private String password;

    /** 사용자 권한: ADMIN / OWNER / CUSTOMER */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /** 사용자 표시명(닉네임) */
    @Column(length = 30)
    private String nickname;

    /** 사용자 전화번호 */
    @Column(length = 20)
    private String phoneNumber;

    /**
     * [역할]
     * - 객체 생성 시 필수 속성만 노출하여 안전하게 User를 만든다.
     * [기능]
     * - 회원가입 시 기본 권한을 CUSTOMER로 설정
     */
    @Builder
    public User(String email, String password, String nickname, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.role = Role.CUSTOMER; // 기본 권한
    }

    /** 닉네임 변경(유효성은 서비스/DTO 단에서 보장) */
    public void updateNickname(String nickname) { this.nickname = nickname; }

    /** 전화번호 변경 */
    public void updatePhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    /** 권한 변경 (관리자 기능 등에서 사용) */
    public void updateRole(Role role) { this.role = role; }
}
