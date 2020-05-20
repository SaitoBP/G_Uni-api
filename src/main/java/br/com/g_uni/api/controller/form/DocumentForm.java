package br.com.g_uni.api.controller.form;

import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DocumentForm {
    @NotNull private DocumentType documentType;
    @NotNull @NotEmpty private String auxiliar;

    // Método de conversão "form" -> "object"
    public Document convert() {
        return new Document(documentType, auxiliar);
    }

    // Getters & Setters
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
