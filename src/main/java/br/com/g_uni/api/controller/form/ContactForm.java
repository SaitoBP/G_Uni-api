package br.com.g_uni.api.controller.form;

import br.com.g_uni.api.model.Contact;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContactForm {

    // Atributos a serem cadastrados
    @NotNull @NotEmpty private String managerName;
    @NotNull @NotEmpty private String managerPhone;
    @NotNull @NotEmpty private String managerEmail;

    @NotNull private String contactName;
    @NotNull private String contactPhone;
    @NotNull private String contactEmail;

    // Converte: ContactForm -> Contact
    public Contact convert() {
        return new Contact(managerName, managerPhone, managerEmail,
                           contactName, contactPhone, contactEmail);
    }

    // Getters & Setters
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
