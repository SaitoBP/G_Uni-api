package br.com.g_uni.api.controller.dto;

import br.com.g_uni.api.model.Company;
import br.com.g_uni.api.model.DataCollection;
import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.Os;
import br.com.g_uni.api.model.others.Branch;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public class OsDto {

    // Atributos a serem devolvidos:
    private Long id;
    private String osNumber;
    private LocalDate osEmissionDate;
    private LocalDate osDeliveryDate;
    private LocalDate osValidityDate;
    private Branch uo;
    private Company company;
    private DataCollection dataCollection;
    private List<Document> documents;

    // Constutor:
    public OsDto(Os os) {
        this.id = os.getId();
        this.osNumber = os.getOsNumber();
        this.osEmissionDate = os.getOsEmissionDate();
        this.osDeliveryDate = os.getOsDeliveryDate();
        this.osValidityDate = os.getOsValidityDate();
        this.uo = os.getUo();
        this.company = os.getCompany();
        this.dataCollection = os.getDataCollection();
        this.documents = os.getDocuments();
    }

    // Converte: OsDto -> Os
    public static Page<OsDto> convert(Page<Os> os) {
        return os.map(OsDto::new);
    }

    // Getters & Setters:
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getOsNumber() {
        return osNumber;
    }
    public void setOsNumber(String osNumber) {
        this.osNumber = osNumber;
    }
    public LocalDate getOsEmissionDate() {
        return osEmissionDate;
    }
    public void setOsEmissionDate(LocalDate osEmissionDate) {
        this.osEmissionDate = osEmissionDate;
    }
    public LocalDate getOsDeliveryDate() {
        return osDeliveryDate;
    }
    public void setOsDeliveryDate(LocalDate osDeliveryDate) {
        this.osDeliveryDate = osDeliveryDate;
    }
    public LocalDate getOsValidityDate() {
        return osValidityDate;
    }
    public void setOsValidityDate(LocalDate osValidityDate) {
        this.osValidityDate = osValidityDate;
    }
    public Branch getUo() {
        return uo;
    }
    public void setUo(Branch uo) {
        this.uo = uo;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public DataCollection getDataCollection() {
        return dataCollection;
    }
    public void setDataCollection(DataCollection dataCollection) {
        this.dataCollection = dataCollection;
    }
    public List<Document> getDocuments() {
        return documents;
    }
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
