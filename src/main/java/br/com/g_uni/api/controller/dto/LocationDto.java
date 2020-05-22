package br.com.g_uni.api.controller.dto;

import br.com.g_uni.api.model.Location;
import org.springframework.data.domain.Page;

public class LocationDto {

    // Atributos
    private Long id;
    private String city;
    private String address;
    private String zipCode;

    // Construtor
    public LocationDto(Location location) {
        this.id = location.getId();
        this.city = location.getCity();
        this.address = location.getAddress();
        this.zipCode = location.getZipCode();
    }

    // Converte: Location -> LocationDto
    public static Page<LocationDto> convert(Page<Location> locations) {
        return locations.map(LocationDto::new);
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
