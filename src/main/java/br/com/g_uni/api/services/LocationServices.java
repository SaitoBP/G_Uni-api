package br.com.g_uni.api.services;

import br.com.g_uni.api.controller.dto.LocationDto;
import br.com.g_uni.api.controller.form.LocationForm;
import br.com.g_uni.api.controller.form.update.LocationUpdate;
import br.com.g_uni.api.model.Location;
import br.com.g_uni.api.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.channels.ReadPendingException;
import java.util.Optional;

@Service
public class LocationServices {

    // Injeção de dependencias
    @Autowired private LocationRepository locationRepository;

    // Método para listar todos os locais
    public Page<LocationDto> listAllLocations(Pageable pagination) {
        Page<Location> locations = locationRepository.findAll(pagination);
        return LocationDto.convert(locations);
    }

    // Método para puxar um local pelo seu id
    public ResponseEntity<LocationDto> getLocationById(Long id) {
        Optional<Location> byId = locationRepository.findById(id);
        return byId.map(location -> ResponseEntity.ok(new LocationDto(location))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para cadastrar um novo local
    public ResponseEntity<LocationDto> createLocation(LocationForm form,
                                                      UriComponentsBuilder uriBuilder) {
        // Converte: LocationForm -> Location
        Location location = form.convert();

        // Salva o local no banco de dados
        locationRepository.save(location);

        // Cria a uri
        URI uri = uriBuilder.path("/location/{id}").buildAndExpand(location.getId()).toUri();

        return ResponseEntity.created(uri).body(new LocationDto(location));
    }

    // Método para atualizar todas as informações de um local pelo seu id
    public ResponseEntity<LocationDto> updateLocationById(Long id, LocationUpdate update) {
        Optional<Location> byId = locationRepository.findById(id);
        if (byId.isPresent()) {
            Location location = update.update(id, locationRepository);
            return ResponseEntity.ok(new LocationDto(location));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar um local pelo seu id
    public ResponseEntity<?> deleteLocationById(Long id) {
        Optional<Location> byId = locationRepository.findById(id);
        if (byId.isPresent()) {
            locationRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
