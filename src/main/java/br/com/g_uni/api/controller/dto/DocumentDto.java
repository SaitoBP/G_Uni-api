package br.com.g_uni.api.controller.dto;

import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentStatus;
import br.com.g_uni.api.model.others.DocumentType;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class DocumentDto {

    // Atributos
    private Long id;
    private DocumentStatus docStatus;
    private DocumentType docType;
    private String attribuitionDate;
    private String finishDate;
    private String sendToValidationDate;
    private String auxiliar;

    // Construtor
    public DocumentDto(Document document) {
        this.id = document.getId();
        this.docStatus = document.getDocStatus();
        this.docType = document.getDocType();
        this.attribuitionDate = document.getAttribuitionDate();
        this.finishDate = document.getFinishDate();
        this.sendToValidationDate = document.getSendToValidationDate();
        this.auxiliar = document.getAuxiliar();
    }

    // Método de conversão document -> documentDto
    public static Page<DocumentDto> convert(Page<Document> documents) {
        return documents.map(DocumentDto::new);
    }

    // Getters - Não é necessario Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public DocumentStatus getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(DocumentStatus docStatus) {
        this.docStatus = docStatus;
    }

    public DocumentType getDocType() {
        return docType;
    }

    public void setDocType(DocumentType docType) {
        this.docType = docType;
    }

    public String getAttribuitionDate() {
        return attribuitionDate;
    }
    public void setAttribuitionDate(String attribuitionDate) {
        this.attribuitionDate = attribuitionDate;
    }
    public String getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
    public String getSendToValidationDate() {
        return sendToValidationDate;
    }
    public void setSendToValidationDate(String sendToValidationDate) {
        this.sendToValidationDate = sendToValidationDate;
    }
    public String getAuxiliar() {
        return auxiliar;
    }
    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }
}
