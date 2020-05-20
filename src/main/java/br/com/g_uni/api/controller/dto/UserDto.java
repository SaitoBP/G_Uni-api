package br.com.g_uni.api.controller.dto;

import br.com.g_uni.api.model.User;
import br.com.g_uni.api.model.others.UserType;
import org.springframework.data.domain.Page;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    private Long id;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    // Construtor
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userType = user.getUserType();
    }

    // Método que converte um objeto User em um UserDto
    public static Page<UserDto> convert(Page<User> users) {
        return users.map(UserDto::new);
    }

    // Getters - Não é necessario Setters
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public UserType getUserType() {
        return userType;
    }
}
