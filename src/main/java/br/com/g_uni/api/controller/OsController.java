package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.OsDto;
import br.com.g_uni.api.controller.form.OsForm;
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
                                                  sort = "id")Pageable pagination) {
        return osServices.listAllOs(pagination);
    }

    // HTTP POST - Cria uma nova OS
    @PostMapping @Transactional
    public ResponseEntity<OsDto> createOs(@RequestBody @Valid OsForm form,
                                          UriComponentsBuilder uriBuilder) {
        return osServices.createOs(form, uriBuilder);
    }
}
