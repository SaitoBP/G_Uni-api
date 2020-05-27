package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.DataCollectionDto;
import br.com.g_uni.api.controller.form.DataCollectionForm;
import br.com.g_uni.api.services.DataCollectionServices;
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

@RestController @RequestMapping("/dataCollection")
public class DataCollectionController {

    // Injeção de dependencias
    @Autowired private DataCollectionServices dataCollectionServices;

    // HTTP GET - Listar as informações de coleta de todas as OS's
    @GetMapping
    public Page<DataCollectionDto> listAllDataCollections(@PageableDefault(page = 0, size = 10,
                                                                           direction = Sort.Direction.ASC, sort = "id")Pageable pagination) {
        return dataCollectionServices.listAllDataCollections(pagination);
    }

    // HTTP POST - Cadastra novas informações de uma OS
    @PostMapping @Transactional
    public ResponseEntity<DataCollectionDto> createDataCollection(@RequestBody @Valid DataCollectionForm form,
                                                                  UriComponentsBuilder uriBuilder) {
        return dataCollectionServices.createDataCollection(form, uriBuilder);
    }
}
