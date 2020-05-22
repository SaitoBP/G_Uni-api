package br.com.g_uni.api.controller.form.update;

import br.com.g_uni.api.model.Location;
import br.com.g_uni.api.repository.LocationRepository;

public class LocationUpdate {

    // Atributos a serem atualizados
    private String city;
    private String address;
    private String zipCode;

    // Método para atualizar um local
    public Location update(Long id, LocationRepository repository) {

        // Procura um local no banco de dados pelo seu id
        Location location = repository.getOne(id);

        location.setCity(this.city);
        location.setAddress(this.address);
        location.setZipCode(this.zipCode);

        return location;
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
