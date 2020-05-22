package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.LocationDto;
import br.com.g_uni.api.controller.form.LocationForm;
import br.com.g_uni.api.controller.form.update.LocationUpdate;
import br.com.g_uni.api.services.LocationServices;
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

@RestController @RequestMapping("/location")
public class LocationController {

    // Injeção de dependencias
    @Autowired private LocationServices locationServices;

    // HTTP GET - Lista todos os locais
    @GetMapping
    public Page<LocationDto> listAllLocations(@PageableDefault(page = 0, size = 10, sort = "id",
                                                               direction = Sort.Direction.ASC) Pageable pagination) {
        return locationServices.listAllLocations(pagination);
    }

    // HTTP GET - Puxa um local pelo seu id
    @GetMapping("/id:{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable Long id) {
        return locationServices.getLocationById(id);
    }

    // HTTP POST - Cadastra um novo local
    @PostMapping @Transactional
    public ResponseEntity<LocationDto> createLocation(@RequestBody @Valid LocationForm form,
                                                      UriComponentsBuilder uriBuilder) {
        return locationServices.createLocation(form, uriBuilder);
    }

    // HTTP PUT - Atualiza todas as informações de um local pelo seu id
    @PutMapping("/id:{id}") @Transactional
    public ResponseEntity<LocationDto> updateLocationByid(@PathVariable Long id,
                                                          @RequestBody @Valid LocationUpdate update) {
        return locationServices.updateLocationById(id, update);
    }

    // HTTP DELETE - Deleta um local pelo seu id
    @DeleteMapping("/id:{id}")
    public ResponseEntity<?> deleteLocationById(@PathVariable Long id) {
        return locationServices.deleteLocationById(id);
    }
}
