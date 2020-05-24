package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.InformationDto;
import br.com.g_uni.api.controller.form.InformationForm;
import br.com.g_uni.api.controller.form.update.InformationUpdate;
import br.com.g_uni.api.services.InformationServices;
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

@RestController @RequestMapping("/information")
public class InformationController {

    // Injeção de dependencias
    @Autowired private InformationServices informationServices;

    // HTTP GET - Listar todas as informações de todas as empresas
    @GetMapping
    public Page<InformationDto> listAllInformations(@PageableDefault(page = 0, size = 10, sort = "id",
                                                                     direction = Sort.Direction.ASC)Pageable pagination) {
        return informationServices.listAllInformations(pagination);
    }

    // HTTP GET - Puxa as informações de uma empresa pelo seu id
    @GetMapping("/id:{id}")
    public ResponseEntity<InformationDto> getInformationById(@PathVariable Long id) {
        return informationServices.getInformationById(id);
    }

    // HTTP POST - Cadastra novas informações de uma empresa
    @PostMapping @Transactional
    public ResponseEntity<InformationDto> createInformation(@RequestBody @Valid InformationForm form,
                                                            UriComponentsBuilder uriBuilder) {
        return informationServices.createInformation(form, uriBuilder);
    }

    // HTTP PUT - Atualiza todas as informações de uma empresa pelo seu id
    @PutMapping("/id:{id}") @Transactional
    public ResponseEntity<InformationDto> updateInformationById(@PathVariable Long id,
                                                                @RequestBody @Valid InformationUpdate update) {
        return informationServices.updateInformationById(id, update);
    }

    // HTTP DELETE - Deleta as informações de uma empresa pelo seu id
    @DeleteMapping("/id:{id}") @Transactional
    public ResponseEntity<?> deleteInformationById(@PathVariable Long id) {
        return informationServices.deleteInformationById(id);
    }
}
