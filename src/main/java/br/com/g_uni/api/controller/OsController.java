package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.OsDto;
import br.com.g_uni.api.controller.form.OsForm;
import br.com.g_uni.api.controller.form.patch.OsPatchDocument;
import br.com.g_uni.api.model.others.Branch;
import br.com.g_uni.api.services.OsServices;
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

@RestController @RequestMapping("/os")
public class OsController {

    // Injeção de dependencias
    @Autowired private OsServices osServices;

    // HTTP GET - Lista todas as OS's
    @GetMapping
    public Page<OsDto> listAllOs(@PageableDefault(page = 0, size = 10,
                                                  direction = Sort.Direction.ASC,
                                                  sort = "id") Pageable pagination,
                                 @RequestParam(required = true) Branch uo) {
        return osServices.listAllOs(pagination, uo);
    }

    // HTTP GET - Puxa uma OS pelo seu id
    @GetMapping("/id:{id}")
    public ResponseEntity<OsDto> getOsById(@PathVariable Long id) {
        return osServices.getOsById(id);
    }

    // HTTP POST - Cria uma nova OS
    @PostMapping @Transactional
    public ResponseEntity<OsDto> createOs(@RequestBody @Valid OsForm form,
                                          UriComponentsBuilder uriBuilder) {
        return osServices.createOs(form, uriBuilder);
    }

    // HTTP PATCH - Adiciona um documento na OS pelo seu id
    @PatchMapping("/id:{id}") @Transactional
    public ResponseEntity<OsDto> patchOsDocumentsById(@PathVariable Long id,
                                                      @RequestBody @Valid OsPatchDocument form) {
        return osServices.patchOsDocumentsById(id, form);
    }
}
