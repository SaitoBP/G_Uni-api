package br.com.g_uni.api.controller.form;

import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DocumentForm {

    // Atributos a serem adicionados:
    private DocumentType docType;
    private String auxiliar;
    private String attribuitionDate;
    private String sendToValidationDate;
    private String finishDate;

    // Converte: DocumentForm -> Document
    public Document convert() {
        return new Document(docType, auxiliar, attribuitionDate, sendToValidationDate, finishDate);
    }

    // Getters & Setters
    public DocumentType getDocType() {
        return docType;
    }
    public void setDocType(DocumentType docType) {
        this.docType = docType;
    }
    public String getAuxiliar() {
        return auxiliar;
    }
    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }
    public String getAttribuitionDate() {
        return attribuitionDate;
    }
    public void setAttribuitionDate(String attribuitionDate) {
        this.attribuitionDate = attribuitionDate;
    }
    public String getSendToValidationDate() {
        return sendToValidationDate;
    }
    public void setSendToValidationDate(String sendToValidationDate) {
        this.sendToValidationDate = sendToValidationDate;
    }
    public String getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}
