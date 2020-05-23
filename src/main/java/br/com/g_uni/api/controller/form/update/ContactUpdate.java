package br.com.g_uni.api.controller.form.update;

import br.com.g_uni.api.model.Contact;
import br.com.g_uni.api.repository.ContactRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContactUpdate {

    // Atributos a serem atualizados
    @NotNull @NotEmpty private String managerName;
    @NotNull @NotEmpty private String managerPhone;
    @NotNull @NotEmpty private String managerEmail;

    @NotNull private String contactName;
    @NotNull private String contactPhone;
    @NotNull private String contactEmail;

    // Método para atualizar as informações do contato pelo seu id
    public Contact update(Long id, ContactRepository contactRepository) {

        // Procura um contato no banco de dados pelo seu id
        Contact contact = contactRepository.getOne(id);

        contact.setManagerName(this.managerName);
        contact.setManagerPhone(this.managerPhone);
        contact.setManagerEmail(this.managerEmail);

        contact.setContactName(this.contactName);
        contact.setContactPhone(this.contactPhone);
        contact.setContactEmail(this.contactEmail);

        return contact;
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
