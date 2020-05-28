package br.com.g_uni.api.controller.form.update;

import br.com.g_uni.api.model.DataCollection;
import br.com.g_uni.api.repository.DataCollectionRepository;

import java.time.LocalDate;

public class DataCollectionUpdate {

    // Atributos a serem atualidos
    private LocalDate collectDate;
    private LocalDate sendToPacDate;

    // Atualiza as informações da entidade
    public DataCollection update(Long id, DataCollectionRepository dataCollectionRepository) {
        DataCollection dataCollection = dataCollectionRepository.getOne(id);
        dataCollection.setCollectDate(this.collectDate);
        dataCollection.setSendToPacDate(this.sendToPacDate);

        return dataCollection;
    }

    // Getters & Setters
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
