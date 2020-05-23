package br.com.g_uni.api.services;

import br.com.g_uni.api.controller.dto.ContactDto;
import br.com.g_uni.api.controller.form.ContactForm;
import br.com.g_uni.api.controller.form.update.ContactUpdate;
import br.com.g_uni.api.model.Contact;
import br.com.g_uni.api.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class ContactServices {

    // Injeção de dependencias
    @Autowired private ContactRepository contactRepository;

    // Metodo para listar todos os contatos
    public Page<ContactDto> listAllContacts(Pageable pagination) {
        Page<Contact> all = contactRepository.findAll(pagination);
        return ContactDto.convert(all);
    }

    // Método para puxar um contato pelo seu id
    public ResponseEntity<ContactDto> getContactById(Long id) {
        Optional<Contact> byId = contactRepository.findById(id);
        return byId.map(contact -> ResponseEntity.ok(new ContactDto(contact))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para cadastrar um novo contato
    public ResponseEntity<ContactDto> createContact(ContactForm form,
                                                    UriComponentsBuilder uriBuilder) {
        // Converte: ContactForm -> Contact
        if (form.getContactName().isEmpty() &&
            form.getContactPhone().isEmpty() &&
            form.getContactEmail().isEmpty()) {

            form.setContactName(form.getManagerName());
            form.setContactPhone(form.getManagerPhone());
            form.setContactEmail(form.getManagerEmail());
        }
        Contact contact = form.convert();

        // Salva o contato no banco de dados
        contactRepository.save(contact);

        // Cria a uri
        URI uri = uriBuilder.path("/contact/{id}").buildAndExpand(contact.getId()).toUri();

        return ResponseEntity.created(uri).body(new ContactDto(contact));
    }

    // Método para atualizar todas as informações de um contato pelo seu id
    public ResponseEntity<ContactDto> updateContactById(Long id, ContactUpdate form) {
        Optional<Contact> byId = contactRepository.findById(id);
        if (byId.isPresent()) {
            if (form.getContactName().isEmpty() &&
                form.getContactPhone().isEmpty() &&
                form.getContactEmail().isEmpty()) {

                form.setContactName(form.getManagerName());
                form.setContactPhone(form.getManagerPhone());
                form.setContactEmail(form.getManagerEmail());
            }
            Contact contact = form.update(id, contactRepository);
            return ResponseEntity.ok(new ContactDto(contact));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar um contato pelo seu id
    public ResponseEntity<?> deleteContactById(Long id) {
        Optional<Contact> byId = contactRepository.findById(id);
        if (byId.isPresent()) {
            contactRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
