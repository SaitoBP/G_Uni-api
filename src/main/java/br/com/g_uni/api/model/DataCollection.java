package br.com.g_uni.api.model;

import br.com.g_uni.api.model.others.CollectStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DataCollection {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DATA_COLLECTION_ID") private Long id;

    @Enumerated(EnumType.STRING)
    private CollectStatus collectStatus = CollectStatus.COLETA_AGENDADA;
    private LocalDate collectDate;
    private LocalDate sendToPacDate;

    // Construtor padrão - OBRIGATORIO
    public DataCollection() {
    }

    // Construtor usado no DataCollectionForm
    public DataCollection(LocalDate collectDate, LocalDate sendToPacDate) {
        this.collectDate = collectDate;
        this.sendToPacDate = sendToPacDate;
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
