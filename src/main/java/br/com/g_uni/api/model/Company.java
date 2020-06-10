package br.com.g_uni.api.model;

import br.com.g_uni.api.controller.dto.LocationDto;

import javax.persistence.*;

@Entity
public class Company {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID") private Long id;

    @Column(name = "COMPANY_NAME") private String companyName;
    private String cnpj;
    private String cnae;

    // Composições
    @OneToOne(cascade = CascadeType.ALL) private Location location;
    @OneToOne(cascade = CascadeType.ALL) private Information information;

    // Construtor padrão - OBRIGATORIO
    public Company() {
    }

    // Construtor usado no CompanyForm
    public Company(String companyName, String cnpj, String cnae, Location location, Information information) {
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.cnae = cnae;
        this.location = location;
        this.information = information;
    }

    // Getter & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getCnae() {
        return cnae;
    }
    public void setCnae(String cnae) {
        this.cnae = cnae;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Information getInformation() {
        return information;
    }
    public void setInformation(Information information) {
        this.information = information;
    }
}
