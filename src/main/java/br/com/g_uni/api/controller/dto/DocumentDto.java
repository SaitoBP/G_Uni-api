package br.com.g_uni.api.controller.dto;

import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentStatus;
import br.com.g_uni.api.model.others.DocumentType;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class DocumentDto {

    // Atributos
    private Long documentId;
    private DocumentStatus documentStatus;
    private DocumentType documentType;
    private LocalDate documentAttribuitionDate;
    private LocalDate documentFinishDate;
    private LocalDate documentSendToValidationDate;
    private String auxiliar;

    // Construtor
    public DocumentDto(Document document) {
        this.documentId = document.getId();
        this.documentStatus = document.getDocStatus();
        this.documentType = document.getDocType();
        this.documentAttribuitionDate = document.getAttribuitionDate();
        this.documentFinishDate = document.getFinishDate();
        this.documentSendToValidationDate = document.getSendToValidationDate();
        this.auxiliar = document.getAuxiliar();
    }

    // Método de conversão document -> documentDto
    public static Page<DocumentDto> convert(Page<Document> documents) {
        return documents.map(DocumentDto::new);
    }

    // Getters - Não é necessario Setters
    public Long getDocumentId() {
        return documentId;
    }
    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }
    public DocumentType getDocumentType() {
        return documentType;
    }
    public LocalDate getDocumentAttribuitionDate() {
        return documentAttribuitionDate;
    }
    public LocalDate getDocumentFinishDate() {
        return documentFinishDate;
    }
    public LocalDate getDocumentSendToValidationDate() {
        return documentSendToValidationDate;
    }
    public String getAuxiliar() {
        return auxiliar;
    }
}
