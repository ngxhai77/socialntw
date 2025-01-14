package com.example.socialntw.repository;

import com.example.socialntw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findByIsActiveTrue();

    Optional<User> findByIdAndIsActiveTrue(Integer id);

    Optional<User> findByRefreshToken(String refreshToken);

    boolean existsByEmailAndIdNot(String email, Integer id);

    Optional<User> findById(Integer userId);
}
