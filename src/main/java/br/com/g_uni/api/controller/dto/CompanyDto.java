package br.com.g_uni.api.controller.dto;

import br.com.g_uni.api.model.Company;
import br.com.g_uni.api.model.Information;
import br.com.g_uni.api.model.Location;
import org.springframework.data.domain.Page;

public class CompanyDto {

    // Atributos a serem devolvidos
    private Long id;

    private String companyName;
    private String cnpj;
    private String cnae;

    private Location location;
    private Information information;

    // Construtor


    public CompanyDto(Company company) {
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        this.cnpj = company.getCnpj();
        this.cnae = company.getCnae();
        this.location = company.getLocation();
        this.information = company.getInformation();
    }

    // Converte: Document -> DocumentDto
    public static Page<CompanyDto> convert(Page<Company> companies) {
        return companies.map(CompanyDto::new);
    }

    // Getters & Setters
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
