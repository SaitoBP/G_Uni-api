package br.com.g_uni.api.controller.form.update;

import br.com.g_uni.api.model.Contact;
import br.com.g_uni.api.model.Information;
import br.com.g_uni.api.repository.ContactRepository;
import br.com.g_uni.api.repository.InformationRepositoy;

public class InformationUpdate {

    // Atributos a serem atualizados
    private String employees;
    private String sectors;
    private String positions;
    private Long contactId;

    // Método para atualizar as informações
    public Information update(Long id, InformationRepositoy informationRepositoy, ContactRepository contactRepository) {

        Information information = informationRepositoy.getOne(id);
        information.setEmployees(this.employees);
        information.setSectors(this.sectors);
        information.setPositions(this.positions);

        Contact contact = contactRepository.getOne(this.contactId);
        information.setContact(contact);

        return information;
    }

    // Getters & Setters
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
