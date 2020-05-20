package br.com.g_uni.api.controller.form;

import br.com.g_uni.api.model.User;
import br.com.g_uni.api.model.others.UserType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserForm {
    @NotNull @NotEmpty private String name;
    @NotNull @NotEmpty private String email;
    @NotNull @NotEmpty private String password;
    @NotNull private UserType userType;

    // Método de conversão Form -> Object
    public User convert() {
        return new User(name, email, password, userType);
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
