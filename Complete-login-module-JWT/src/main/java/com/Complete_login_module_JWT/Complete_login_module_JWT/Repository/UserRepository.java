package com.Complete_login_module_JWT.Complete_login_module_JWT.Repository;

import com.Complete_login_module_JWT.Complete_login_module_JWT.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);
}
