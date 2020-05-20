package br.com.g_uni.api.model;

import br.com.g_uni.api.model.others.Branch;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Os {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String osNumber;
    private LocalDate osEmissionDate; // Data de emissão
    private LocalDate osDeliveryDate; // Data de entrega
    private LocalDate osValidityDate; // Vigencia

    @Enumerated(EnumType.STRING)
    private Branch uo;

    // Composições
    @ManyToOne private Company company;
    @OneToOne private DataCollection dataCollection;

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
}
