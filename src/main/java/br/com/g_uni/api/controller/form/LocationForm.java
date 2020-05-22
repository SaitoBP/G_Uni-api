package br.com.g_uni.api.controller.form;

import br.com.g_uni.api.model.Location;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LocationForm {

    // Atributos a serem adicionados
    @NotNull @NotEmpty private String city;
    @NotNull @NotEmpty private String address;
    @NotNull @NotEmpty private String zipCode;

    // Converte: LocationForm -> Location
    public Location convert() {
        return new Location(city, address, zipCode);
    }

    // Getters & Setters
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
