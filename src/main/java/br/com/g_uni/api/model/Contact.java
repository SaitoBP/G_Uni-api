package br.com.g_uni.api.model;

import javax.persistence.*;

@Entity
public class Contact {

    // Atributos
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACT_ID") private Long id;

    @Column(name = "MANAGER_NAME") private String managerName;
    @Column(name = "MANAGER_PHONE") private String managerPhone;
    @Column(name = "MANAGER_EMAIL") private String managerEmail;

    @Column(name = "CONTACT_NAME") private String contactName;
    @Column(name = "CONTACT_PHONE") private String contactPhone;
    @Column(name = "CONTACT_EMAIL") private String contactEmail;

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public String getManagerPhone() {
        return managerPhone;
    }
    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }
    public String getManagerEmail() {
        return managerEmail;
    }
    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }
}
