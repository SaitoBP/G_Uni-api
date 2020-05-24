package br.com.g_uni.api.controller.form;

import br.com.g_uni.api.model.Contact;
import br.com.g_uni.api.model.Information;
import br.com.g_uni.api.repository.ContactRepository;

import java.util.Optional;

public class InformationForm {

    // Atributos a serem cadastrados
    private String employees;
    private String sectors;
    private String positions;
    private Long contactId;

    // Converte: InformationForm -> Information
    public Information convert(ContactRepository repository) {
        Contact contact = repository.getOne(contactId);
        return new Information(employees, sectors, positions, contact);
    }

    // Getters & Setters;
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
    public Long getContactId() {
        return contactId;
    }
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
