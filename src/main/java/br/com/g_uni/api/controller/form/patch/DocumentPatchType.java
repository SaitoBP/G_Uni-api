package br.com.g_uni.api.controller.form.patch;

import br.com.g_uni.api.model.others.DocumentType;

import javax.validation.constraints.NotNull;

public class DocumentPatchType {
    // Atributo a ser atualizado
    @NotNull private DocumentType documentType;

    // Getters & Setters
    public DocumentType getDocumentType() {
        return documentType;
    }
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}
