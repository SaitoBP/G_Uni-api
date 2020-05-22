package br.com.g_uni.api.controller.form;

import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DocumentForm {
    @NotNull private DocumentType type;
    @NotNull @NotEmpty private String auxiliar;

    // Converte: DocumentForm -> Document
    public Document convert() {
        return new Document(type, auxiliar);
    }

    // Getters & Setters
    public DocumentType getType() {
        return type;
    }
    public void setType(DocumentType type) {
        this.type = type;
    }
    public String getAuxiliar() {
        return auxiliar;
    }
    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }
}
