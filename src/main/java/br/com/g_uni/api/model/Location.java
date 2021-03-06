package br.com.g_uni.api.model;

import javax.persistence.*;

@Entity
public class Location {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID") private Long id;
    private String city;
    private String address;
    @Column(name = "ZIP_CODE") private String zipCode; // CEP

    // Construtor padrão - OBRIGATÓRIO
    public Location() {
    }

    // Construtor usado no LocationForm
    public Location(String city, String address, String zipCode) {
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
