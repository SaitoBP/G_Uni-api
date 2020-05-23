package br.com.g_uni.api.model;

import javax.persistence.*;

@Entity
public class Information {

    // Atributos
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INFO_ID") private Long id;
    @Column(name = "EMPLOYEES_NUMBER") private String employees;
    @Column(name = "SECTORS_NUMBER") private String sectors;
    @Column(name = "POSITIONS_NUMBER") private String positions; // Cargos

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
    public String getEmployees() {
        return employees;
    }
    public void setEmployees(String employees) {
        this.employees = employees;
    }
    public String getSectors() {
        return sectors;
    }
    public void setSectors(String sectors) {
        this.sectors = sectors;
    }
    public String getPositions() {
        return positions;
    }
    public void setPositions(String positions) {
        this.positions = positions;
    }
    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
