package br.com.g_uni.api.model;

import javax.persistence.*;

@Entity
public class Information {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer employees;
    private Integer sectors;
    private Integer roles; // Cargos

    // Composições
    @OneToOne
    private Contact contact;

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getEmployees() {
        return employees;
    }
    public void setEmployees(Integer employees) {
        this.employees = employees;
    }
    public Integer getSectors() {
        return sectors;
    }
    public void setSectors(Integer sectors) {
        this.sectors = sectors;
    }
    public Integer getRoles() {
        return roles;
    }
    public void setRoles(Integer roles) {
        this.roles = roles;
    }
    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
