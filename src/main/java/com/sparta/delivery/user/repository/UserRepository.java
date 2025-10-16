package com.sparta.delivery.user.repository;

import com.sparta.delivery.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * [역할]
 * - User 엔티티의 데이터베이스 접근 계층 (DAO)
 * - Spring Data JPA를 통해 CRUD 기능을 자동으로 제공받음.
 *
 * [주요 기능]
 * - findByEmail(String email): 이메일을 기반으로 회원 정보 조회
 *   → 로그인 시 사용자 검증, 회원가입 시 중복 확인에 사용
 *
 * [상속]
 * - JpaRepository<User, Long>
 *   → save(), findById(), findAll(), delete() 등의 기본 메서드 자동 제공
 *
 * [사용 위치]
 * - AuthService, UserService 등 비즈니스 계층
 *
 * [비고]
 * - @Repository 어노테이션을 통해 스프링 빈으로 등록되어
 *   트랜잭션 예외 처리(DataAccessException) 계층의 일원으로 동작함.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * [기능]
     * - 이메일로 회원 정보를 조회한다.
     *   (이메일은 고유값으로 설계되어야 함)
     *
     * @param email 회원 이메일
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);
}
package com.sparta.delivery.user.repository;

import com.sparta.delivery.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * [역할]
 * - User 엔티티의 데이터베이스 접근 계층 (DAO)
 * - Spring Data JPA를 통해 CRUD 기능을 자동으로 제공받음.
 *
 * [주요 기능]
 * - findByEmail(String email): 이메일을 기반으로 회원 정보 조회
 *   → 로그인 시 사용자 검증, 회원가입 시 중복 확인에 사용
 *
 * [상속]
 * - JpaRepository<User, Long>
 *   → save(), findById(), findAll(), delete() 등의 기본 메서드 자동 제공
 *
 * [사용 위치]
 * - AuthService, UserService 등 비즈니스 계층
 *
 * [비고]
 * - @Repository 어노테이션을 통해 스프링 빈으로 등록되어
 *   트랜잭션 예외 처리(DataAccessException) 계층의 일원으로 동작함.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * [기능]
     * - 이메일로 회원 정보를 조회한다.
     *   (이메일은 고유값으로 설계되어야 함)
     *
     * @param email 회원 이메일
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);
}
