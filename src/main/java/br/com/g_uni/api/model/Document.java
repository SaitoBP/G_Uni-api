package br.com.g_uni.api.model;

import br.com.g_uni.api.model.others.DocumentStatus;
import br.com.g_uni.api.model.others.DocumentType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Document {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) private DocumentStatus docStatus = DocumentStatus.EM_ELABORAÇÃO;
    @Enumerated(EnumType.STRING) private DocumentType docType;

    private String attribuitionDate;
    private String finishDate;
    private String sendToValidationDate;
    private String auxiliar;

    // Construtor default - OBRIGATORIO
    public Document(){}

    // Construtor usado no DocumentForm
    public Document(DocumentType docType, String auxiliar, String attribuitionDate,
                    String sendToValidationDate, String finishDate) {

        // Atualiza os parametros de 'Document' com os dados do 'DocumentForm':
        this.docType = docType;
        this.auxiliar = auxiliar;
        this.attribuitionDate = attribuitionDate;
        this.sendToValidationDate = sendToValidationDate;
        this.finishDate = finishDate;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public DocumentStatus getDocStatus() {
        return docStatus;
    }
    public void setDocStatus(DocumentStatus docStatus) {
        this.docStatus = docStatus;
    }
    public DocumentType getDocType() {
        return docType;
    }
    public void setDocType(DocumentType docType) {
        this.docType = docType;
    }
    public String getAttribuitionDate() {
        return attribuitionDate;
    }
    public void setAttribuitionDate(String attribuitionDate) {
        this.attribuitionDate = attribuitionDate;
    }
    public String getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
    public String getSendToValidationDate() {
        return sendToValidationDate;
    }
    public void setSendToValidationDate(String sendToValidationDate) {
        this.sendToValidationDate = sendToValidationDate;
    }
    public String getAuxiliar() {
        return auxiliar;
    }
    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }
}
