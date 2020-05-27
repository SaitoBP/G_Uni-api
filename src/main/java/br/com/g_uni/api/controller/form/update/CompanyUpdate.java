package br.com.g_uni.api.controller.form.update;

import br.com.g_uni.api.model.Company;
import br.com.g_uni.api.model.Information;
import br.com.g_uni.api.model.Location;
import br.com.g_uni.api.repository.CompanyRepository;
import br.com.g_uni.api.repository.InformationRepositoy;
import br.com.g_uni.api.repository.LocationRepository;

public class CompanyUpdate {

    // Atributos a serem atualizados
    private String companyName;
    private String cnpj;
    private String cnae;

    private Long locationId;
    private Long informationId;

    // Atualiza as informações da entidade
    public Company update(Long id, CompanyRepository companyRepository,
                       LocationRepository locationRepository,
                       InformationRepositoy informationRepositoy) {

       // Pega a entidade Company do banco de dados com o id fornecido
        Company company = companyRepository.getOne(id);

        company.setCompanyName(this.companyName);
        company.setCnpj(this.cnpj);
        company.setCnae(this.cnae);

        // Pega as informações da entidade Location com o id fornecido
        Location location = locationRepository.getOne(locationId);
        company.setLocation(location);

        // Pega as informações da entidade Information com o id fornecido
        Information information = informationRepositoy.getOne(informationId);
        company.setInformation(information);

        return company;
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
