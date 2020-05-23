package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.ContactDto;
import br.com.g_uni.api.controller.form.ContactForm;
import br.com.g_uni.api.controller.form.update.ContactUpdate;
import br.com.g_uni.api.services.ContactServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController @RequestMapping("/contact")
public class ContactController {

    // Injeção de dependencias
    @Autowired private ContactServices contactServices;

    // HTTP GET - Lista todos os contatos
    @GetMapping
    public Page<ContactDto> listAllContacts(@PageableDefault(page = 0, size = 10, sort = "id",
                                                             direction = Sort.Direction.ASC) Pageable pagination) {
        return contactServices.listAllContacts(pagination);
    }

    // HTTP GET - Puxa um contato pelo seu id
    @GetMapping("/id:{id}")
    public ResponseEntity<ContactDto> getContactById(@PathVariable Long id) {
        return contactServices.getContactById(id);
    }

    // HTTP POST - Cadastra um novo contato
    @PostMapping @Transactional
    public ResponseEntity<ContactDto> createContact(@RequestBody @Valid ContactForm form,
                                                     UriComponentsBuilder uriBuilder) {
        return contactServices.createContact(form, uriBuilder);
    }

    // HTTP PUT - Atualiza todas as informações de um contato pelo seu id
    @PutMapping("/id:{id}") @Transactional
    public ResponseEntity<ContactDto> updateContactById(@PathVariable Long id,
                                                        @RequestBody @Valid ContactUpdate update) {
        return contactServices.updateContactById(id, update);
    }

    // HTTP DELETE - Deleta um contato pelo seu id
    @DeleteMapping("/id:{id}") @Transactional
    public ResponseEntity<?> deleteContactById(@PathVariable Long id) {
        return contactServices.deleteContactById(id);
    }
}
