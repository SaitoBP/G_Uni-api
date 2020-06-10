package br.com.g_uni.api.controller.dto;

import br.com.g_uni.api.model.Information;
import org.springframework.data.domain.Page;

public class InformationDto {

    // Atributos a serem devolvidos
    private Long id;
    private String employees;
    private String sectors;
    private String positions;
    private Long contactId;

    // Construtor
    public InformationDto(Information information) {
        this.id = information.getId();
        this.employees = information.getEmployees();
        this.sectors = information.getSectors();
        this.positions = information.getPositions();
        this.contactId = information.getContact().getId();
    }

    // Converte: Information -> InformationDto
    public static Page<InformationDto> convert(Page<Information> information) {
        return information.map(InformationDto::new);
    }

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
    public Long getContactId() {
        return contactId;
    }
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
