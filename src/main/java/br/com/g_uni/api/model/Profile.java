package br.com.g_uni.api.model;

import br.com.g_uni.api.model.others.UserType;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Profile implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    // Implementa os metodos da interface GrantedAuthority
    @Override public String getAuthority() {
        return userType.name();
    }
}
