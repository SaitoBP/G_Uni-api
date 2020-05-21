package br.com.g_uni.api.controller.form.patch;

import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentStatus;
import br.com.g_uni.api.model.others.DocumentType;
import br.com.g_uni.api.repository.DocumentRepository;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DocumentFormPatch {
    // Atributos
    private DocumentStatus documentStatus;
    private DocumentType documentType;
    private String auxiliar;
    private LocalDate documentFinishDate;
    private LocalDate documentSendToValidationDate;

    // Método para atualizar o status do documento
    public Document patchStatus(Long id, DocumentRepository documentRepository) {
        Document document = documentRepository.getOne(id);
        document.setDocStatus(this.documentStatus);

        return document;
    }

    // Método para atualizar o tipo do documento
    public Document patchType(Long id, DocumentRepository documentRepository) {
        Document document = documentRepository.getOne(id);
        document.setDocType(this.documentType);

        return document;
    }

    // Método para atualizar o auxiliar do documento
    public Document patchAuxiliar(Long id, DocumentRepository documentRepository) {
        Document document = documentRepository.getOne(id);
        document.setAuxiliar(this.auxiliar);

        return document;
    }

    // Método para adicionar/atualizar a data de finalização do documento
    public Document patchFinishDate(Long id, DocumentRepository documentRepository) {
        Document document = documentRepository.getOne(id);
        if (documentFinishDate == null) {
            document.setFinishDate(LocalDate.now());
        } else {
            document.setFinishDate(this.documentFinishDate);
        }
        document.setDocStatus(DocumentStatus.FINALIZADO);

        return document;
    }

    // Método para adcionar/atualizar a data de envio para validação do documento
    public Document patchValidationDate(Long id, DocumentRepository documentRepository) {
        Document document = documentRepository.getOne(id);
        if (documentSendToValidationDate == null) {
            document.setSendToValidationDate(LocalDate.now());
        } else {
            document.setSendToValidationDate(this.documentSendToValidationDate);
        }
        document.setDocStatus(DocumentStatus.VALIDAÇÃO);

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
    public String getAuxiliar() {
        return auxiliar;
    }
    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
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
}
