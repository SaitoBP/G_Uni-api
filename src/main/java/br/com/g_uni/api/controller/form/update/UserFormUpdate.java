package br.com.g_uni.api.controller.form.update;

import br.com.g_uni.api.model.User;
import br.com.g_uni.api.model.others.UserType;
import br.com.g_uni.api.repository.UserRepository;

public class UserFormUpdate {
    private String name;
    private String email;
    private String password;
    private UserType userType;

    // Método para atualizar um usuario
    public User update(Long id, UserRepository userRepository) {

        // Procura um usuario no banco de dados pelo seu id
        User user = userRepository.getOne(id);

        user.setName(this.name);
        user.setEmail(this.email);
        user.setUserPassword(this.password);
        user.setUserType(this.userType);

        return user;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
