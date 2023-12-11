package com.svalero.taesmotors.repository;

import com.svalero.taesmotors.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}