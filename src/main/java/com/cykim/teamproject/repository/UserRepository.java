package com.cykim.teamproject.repository;

import com.cykim.teamproject.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    // 올바른 메서드 정의
    UserEntity findByNickname(String nickname);
    UserEntity findByContact(String contact);
@Transactional
    // 반환 타입 수정: Optional<UserEntity>
    Optional<UserEntity> findByEmail(String username);
}
