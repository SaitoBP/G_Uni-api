package br.com.g_uni.api.controller.form.update;

import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentStatus;
import br.com.g_uni.api.model.others.DocumentType;
import br.com.g_uni.api.repository.DocumentRepository;

import java.time.LocalDate;

public class DocumentFormUpdate {

    // Atributos a serem atualizados
    private DocumentStatus documentStatus;
    private DocumentType documentType;
    private LocalDate documentAttribuitionDate;
    private LocalDate documentFinishDate;
    private LocalDate documentSendToValidationDate;
    private String auxiliar;

    // Método para atualizar um documento
    public Document update(Long id, DocumentRepository repository) {

        // Procura um documento no banco de dados pelo seu id
        Document document = repository.getOne(id);

        document.setDocStatus(this.documentStatus);
        document.setDocType(this.documentType);
        document.setAttribuitionDate(this.documentAttribuitionDate);
        document.setFinishDate(this.documentFinishDate);
        document.setSendToValidationDate(this.documentSendToValidationDate);
        document.setAuxiliar(this.auxiliar);

        return document;
    }

    // Getters & Setters
    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }
    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }
    public DocumentType getDocumentType() {
        return documentType;
    }
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
    public LocalDate getDocumentAttribuitionDate() {
        return documentAttribuitionDate;
    }
    public void setDocumentAttribuitionDate(LocalDate documentAttribuitionDate) {
        this.documentAttribuitionDate = documentAttribuitionDate;
    }
    public LocalDate getDocumentFinishDate() {
        return documentFinishDate;
    }
    public void setDocumentFinishDate(LocalDate documentFinishDate) {
        this.documentFinishDate = documentFinishDate;
    }
    public LocalDate getDocumentSendToValidationDate() {
        return documentSendToValidationDate;
    }
    public void setDocumentSendToValidationDate(LocalDate documentSendToValidationDate) {
        this.documentSendToValidationDate = documentSendToValidationDate;
    }
    public String getAuxiliar() {
        return auxiliar;
    }
    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }
}
