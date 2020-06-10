package br.com.g_uni.api.controller.form;

import br.com.g_uni.api.model.Company;
import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.Os;
import br.com.g_uni.api.model.others.Branch;
import br.com.g_uni.api.model.others.DocumentType;
import br.com.g_uni.api.repository.CompanyRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OsForm {

    // Atributos a serem cadastrados
    private String osNumber;
    private LocalDate osEmissionDate;
    private LocalDate osValidityDate;
    private Branch uo;
    private Long companyId;

    // Converte: OsForm -> Os
    public Os convert(CompanyRepository companyRepository) {
        Company company = companyRepository.getOne(companyId);
        return new Os(osNumber, osEmissionDate, osValidityDate, uo, company);
    }

    // Getters & Setters
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
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
