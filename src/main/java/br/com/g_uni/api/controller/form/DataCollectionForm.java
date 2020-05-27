package br.com.g_uni.api.controller.form;

import br.com.g_uni.api.model.DataCollection;
import br.com.g_uni.api.model.others.CollectStatus;

import java.time.LocalDate;

public class DataCollectionForm {

    // Atributos a serem adicionados
    private LocalDate collectDate;
    private LocalDate sendToPacDate;

    // Converte: DataCollectionForm -> DataCollection
    public DataCollection convert() {
        return new DataCollection(collectDate, sendToPacDate);
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
