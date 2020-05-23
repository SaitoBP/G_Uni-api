package br.com.g_uni.api.controller.dto;

import br.com.g_uni.api.model.Contact;
import org.springframework.data.domain.Page;

public class ContactDto {

    // Atributos a serem devolvidos
    private Long id;
    private String managerName;
    private String managerPhone;
    private String managerEmail;
    private String contactName;
    private String contactPhone;
    private String contactEmail;

    // Construtor
    public ContactDto(Contact contact) {
        this.id = contact.getId();
        this.managerName = contact.getManagerName();
        this.managerPhone = contact.getManagerPhone();
        this.managerEmail = contact.getManagerEmail();
        this.contactName = contact.getContactName();
        this.contactPhone = contact.getContactPhone();
        this.contactEmail = contact.getContactEmail();
    }

    // Converte: Contact -> ContactDto
    public static Page<ContactDto> convert(Page<Contact> contact) {
        return contact.map(ContactDto::new);
    }

    // Getters & Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
}
