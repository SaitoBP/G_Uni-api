package br.com.g_uni.api.model;

import br.com.g_uni.api.model.others.DocumentStatus;
import br.com.g_uni.api.model.others.DocumentType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Document {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) private DocumentStatus docStatus = DocumentStatus.EM_ELABORAÇÃO;
    @Enumerated(EnumType.STRING) private DocumentType docType;

    private LocalDate attribuitionDate = LocalDate.now();
    private LocalDate finishDate;
    private LocalDate sendToValidationDate;
    private String auxiliar;

    // Composições
    @ManyToOne private Os os;

    // Construtor default - OBRIGATORIO
    private Document(){}

    // Construtor usado no DocumentForm
    public Document(DocumentType documentType, String auxiliar) {
        this.docType = documentType;
        this.auxiliar = auxiliar;
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
    public LocalDate getAttribuitionDate() {
        return attribuitionDate;
    }
    public void setAttribuitionDate(LocalDate attribuitionDate) {
        this.attribuitionDate = attribuitionDate;
    }
    public LocalDate getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
    public LocalDate getSendToValidationDate() {
        return sendToValidationDate;
    }
    public void setSendToValidationDate(LocalDate sendToValidationDate) {
        this.sendToValidationDate = sendToValidationDate;
    }
    public String getAuxiliar() {
        return auxiliar;
    }
    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }
    public Os getOs() {
        return os;
    }
    public void setOs(Os os) {
        this.os = os;
    }
}
