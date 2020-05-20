package br.com.g_uni.api.model;

import br.com.g_uni.api.model.others.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String userPassword;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Profile> profile = new ArrayList<>();

    // Construtor default - OBRIGATORIO
    public User() {
    }

    public User(String name, String email, String userPassword, UserType userType) {
        this.name = name;
        this.email = email;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    // Metodos da interface UserDetails
    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profile;
    }
    @Override public String getPassword() {
        return this.userPassword;
    }
    @Override public String getUsername() {
        return this.email;
    }
    @Override public boolean isAccountNonExpired() {
        return true;
    }
    @Override public boolean isAccountNonLocked() {
        return true;
    }
    @Override public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override public boolean isEnabled() {
        return true;
    }
}
