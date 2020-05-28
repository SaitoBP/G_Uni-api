package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.DataCollectionDto;
import br.com.g_uni.api.controller.form.DataCollectionForm;
import br.com.g_uni.api.controller.form.update.DataCollectionUpdate;
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

    // HTTP GET - Puxa informações de coleta de uma OS pelo seu id
    @GetMapping("/id:{id}")
    public ResponseEntity<DataCollectionDto> getDataCollectionById(@PathVariable Long id) {
        return dataCollectionServices.getDataCollectionById(id);
    }

    // HTTP POST - Cadastra novas informações de coleta de uma OS
    @PostMapping @Transactional
    public ResponseEntity<DataCollectionDto> createDataCollection(@RequestBody @Valid DataCollectionForm form,
                                                                  UriComponentsBuilder uriBuilder) {
        return dataCollectionServices.createDataCollection(form, uriBuilder);
    }

    // HTTP PUT - Atualiza todas as informações de coleta de uma OS pelo seu id
    @PutMapping("/id:{id}") @Transactional
    public ResponseEntity<DataCollectionDto> updateDataCollection(@PathVariable Long id,
                                                                  @RequestBody @Valid DataCollectionUpdate form) {
        return dataCollectionServices.updateDataCollection(id, form);
    }

    // HTTP DELETE - Deleta informações de coleta de uma OS pelo seu id
    @DeleteMapping("/id:{id}") @Transactional
    public ResponseEntity<?> deteleDataCollectionById(@PathVariable Long id) {
        return dataCollectionServices.deleteDataCollectionById(id);
    }

}
