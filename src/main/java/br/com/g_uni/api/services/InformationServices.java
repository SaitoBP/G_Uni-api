package br.com.g_uni.api.services;

import br.com.g_uni.api.controller.dto.InformationDto;
import br.com.g_uni.api.controller.form.InformationForm;
import br.com.g_uni.api.controller.form.update.InformationUpdate;
import br.com.g_uni.api.model.Contact;
import br.com.g_uni.api.model.Information;
import br.com.g_uni.api.repository.ContactRepository;
import br.com.g_uni.api.repository.InformationRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class InformationServices {

    // Injeção de dependencias
    @Autowired private InformationRepositoy informationRepositoy;
    @Autowired private ContactRepository contactRepository;

    // Método para listar todas as informações de uma empresa
    public Page<InformationDto> listAllInformations(Pageable pagination) {
        Page<Information> information = informationRepositoy.findAll(pagination);
        return InformationDto.convert(information);
    }

    // Método para puxar as informações de uma empresa pelo seu id
    public ResponseEntity<InformationDto> getInformationById(Long id) {
        Optional<Information> byId = informationRepositoy.findById(id);
        return byId.map(information -> ResponseEntity.ok(new InformationDto(information))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para cadastrar novas informações de uma empresa
    public ResponseEntity<InformationDto> createInformation(InformationForm form,
                                                            UriComponentsBuilder uriBuilder) {
        // Procura no banco de dados se o id do contato fornecido existe
        Optional<Contact> byId = contactRepository.findById(form.getContactId());
        if (byId.isPresent()) {
            // Converte: InformationForm -> Information
            Information information = form.convert(contactRepository);

            // Salva no banco de dados
            informationRepositoy.save(information);

            // Cria a uri
            URI uri = uriBuilder.path("/information/{id}").buildAndExpand(information.getId()).toUri();

            return ResponseEntity.created(uri).body(new InformationDto(information));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para atualizar todas as informações de uma empresa pelo seu id
    public ResponseEntity<InformationDto> updateInformationById(Long id, InformationUpdate form) {
        Optional<Information> byId = informationRepositoy.findById(id);
        if (byId.isPresent()) {
            Optional<Contact> byContactId = contactRepository.findById(form.getContactId());
            if (byContactId.isPresent()) {
                form.update(id, informationRepositoy, contactRepository);
            }
            return ResponseEntity.ok().body(new InformationDto(byId.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deleter as informações de uma empresa pelo seu id
    public ResponseEntity<?> deleteInformationById(Long id) {
        Optional<Information> byId = informationRepositoy.findById(id);
        if (byId.isPresent()) {
            informationRepositoy.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
