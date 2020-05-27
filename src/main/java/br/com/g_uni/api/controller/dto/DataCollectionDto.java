package br.com.g_uni.api.controller.dto;

import br.com.g_uni.api.model.DataCollection;
import br.com.g_uni.api.model.others.CollectStatus;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public class DataCollectionDto {

    // Atributos a serem devolvidos
    private Long id;
    private CollectStatus collectStatus;
    private LocalDate collectDate;
    private LocalDate sendToPacDate;

    // Construtor
    public DataCollectionDto(DataCollection dataCollection) {
        this.id = dataCollection.getId();
        this.collectStatus = dataCollection.getCollectStatus();
        this.collectDate = dataCollection.getCollectDate();
        this.sendToPacDate = dataCollection.getSendToPacDate();
    }

    // Converte: DataCollection -> Data CollectionDto
    public static Page<DataCollectionDto> convert(Page<DataCollection> dataCollection) {
        return dataCollection.map(DataCollectionDto::new);
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public CollectStatus getCollectStatus() {
        return collectStatus;
    }
    public void setCollectStatus(CollectStatus collectStatus) {
        this.collectStatus = collectStatus;
    }
    public LocalDate getCollectDate() {
        return collectDate;
    }
    public void setCollectDate(LocalDate collectDate) {
        this.collectDate = collectDate;
    }
    public LocalDate getSendToPacDate() {
        return sendToPacDate;
    }
    public void setSendToPacDate(LocalDate sendToPacDate) {
        this.sendToPacDate = sendToPacDate;
    }
}
