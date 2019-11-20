package com.app.crud.repository;

import com.app.crud.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Page<User> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);

}
