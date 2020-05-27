package br.com.g_uni.api.controller.form;

import br.com.g_uni.api.model.Company;
import br.com.g_uni.api.model.Information;
import br.com.g_uni.api.model.Location;
import br.com.g_uni.api.repository.InformationRepositoy;
import br.com.g_uni.api.repository.LocationRepository;

public class CompanyForm {

    // Atributos a serem cadastrados
    private String companyName;
    private String cnpj;
    private String cnae;

    private Long locationId;
    private Long informationId;

    // Converte: CompanyForm -> Company
    public Company convert(LocationRepository locationRepository, InformationRepositoy informationRepositoy) {

        // Pega a entidade Location e Information do banco de dados de acordo com o seus id's
        Location location = locationRepository.getOne(locationId);
        Information information = informationRepositoy.getOne(informationId);

        return new Company(companyName, cnpj, cnae, location, information);
    }

    // Getters & Setters
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
    public Long getLocationId() {
        return locationId;
    }
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
    public Long getInformationId() {
        return informationId;
    }
    public void setInformationId(Long informationId) {
        this.informationId = informationId;
    }
}
