package br.com.g_uni.api.controller.form;

import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DocumentForm {

    // Atributos a serem adicionados:
    private DocumentType docType;

    // Converte: DocumentForm -> Document
    public Document convert() {
        return new Document(docType);
    }

    // Getters & Setters
    public DocumentType getDocType() {
        return docType;
    }
    public void setDocType(DocumentType docType) {
        this.docType = docType;
    }
}
