package br.com.g_uni.api.services;

import br.com.g_uni.api.controller.dto.DataCollectionDto;
import br.com.g_uni.api.controller.form.DataCollectionForm;
import br.com.g_uni.api.controller.form.update.DataCollectionUpdate;
import br.com.g_uni.api.model.DataCollection;
import br.com.g_uni.api.model.others.CollectStatus;
import br.com.g_uni.api.repository.DataCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class DataCollectionServices {

    // Injeção de dependencias
    @Autowired private DataCollectionRepository dataCollectionRepository;

    // Método para listar as informações de coleta de todas as OS's
    public Page<DataCollectionDto> listAllDataCollections(Pageable pagination) {
        Page<DataCollection> dataCollections = dataCollectionRepository.findAll(pagination);
        return DataCollectionDto.convert(dataCollections);
    }

    // Método para puxar informações de coleta de uma OS pelo seu id
    public ResponseEntity<DataCollectionDto> getDataCollectionById(Long id) {
        Optional<DataCollection> dataCollectionId = dataCollectionRepository.findById(id);
        return dataCollectionId.map(dataCollection -> ResponseEntity.ok().body(new DataCollectionDto(dataCollection))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para cadastrar novas informações de coleta de uma OS
    public ResponseEntity<DataCollectionDto> createDataCollection(DataCollectionForm form,
                                                                  UriComponentsBuilder uriBuilder) {
        // Converte: DataCollectionForm -> DataCollection
        DataCollection dataCollection = form.convert();

        // Altera o atributo collectStatus de acordo com as datas
        if (form.getCollectDate() != null) {
            dataCollection.setCollectStatus(CollectStatus.COLETA_REALIZADA);
        }

        // Altera o atributo collectStatus de acordo com as datas
        if (form.getSendToPacDate() != null) {
            dataCollection.setCollectStatus(CollectStatus.ENVIADO_AO_PAC);
        }

        // Salva no banco de dados
        dataCollectionRepository.save(dataCollection);

        // Cria a uri
        URI uri = uriBuilder.path("/dataCollection/{id}").buildAndExpand(dataCollection.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataCollectionDto(dataCollection));
    }

    // Método para atualizar todas as informações de coleta de uma OS pelo seu id
    public ResponseEntity<DataCollectionDto> updateDataCollection(Long id,
                                                                  DataCollectionUpdate form) {

        // Verifica se o id informado existe no banco de dados
        Optional<DataCollection> dataCollecionId = dataCollectionRepository.findById(id);
        if (dataCollecionId.isPresent()) {
            DataCollection dataCollection = form.update(id, dataCollectionRepository);
            return ResponseEntity.ok().body(new DataCollectionDto(dataCollection));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar as informações de coleta de uma OS pelo seu id
    public ResponseEntity<?> deleteDataCollectionById(Long id) {
        Optional<DataCollection> dataCollectionId = dataCollectionRepository.findById(id);
        if (dataCollectionId.isPresent()) {
            dataCollectionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
