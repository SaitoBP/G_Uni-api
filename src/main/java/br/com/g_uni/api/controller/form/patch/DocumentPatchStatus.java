package br.com.g_uni.api.controller.form.patch;

import br.com.g_uni.api.controller.dto.DocumentDto;
import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentStatus;

import javax.validation.constraints.NotNull;

public class DocumentPatchStatus {
    // Atributo a ser atualizado
    @NotNull private DocumentStatus documentStatus;

    // Getters & Setters
    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }
    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }
}
