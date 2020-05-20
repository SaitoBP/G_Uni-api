package br.com.g_uni.api.controller.form.patch;

import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentStatus;
import br.com.g_uni.api.model.others.DocumentType;
import br.com.g_uni.api.repository.DocumentRepository;

import javax.validation.constraints.NotNull;

public class DocumentFormPatch {
    // Atributos

    private DocumentStatus documentStatus;
    private DocumentType documentType;
    private String auxiliar;

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
}
