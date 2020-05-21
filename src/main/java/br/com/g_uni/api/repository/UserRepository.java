package br.com.g_uni.api.repository;

import br.com.g_uni.api.model.User;
import br.com.g_uni.api.model.others.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);

    Page<User> findByUserType(UserType userType, Pageable pagination);
}
