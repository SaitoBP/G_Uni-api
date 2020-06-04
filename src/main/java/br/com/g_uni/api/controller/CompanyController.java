package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.CompanyDto;
import br.com.g_uni.api.controller.form.CompanyForm;
import br.com.g_uni.api.controller.form.update.CompanyUpdate;
import br.com.g_uni.api.services.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
@RestController @RequestMapping("/company")
public class CompanyController {

    // Injeção de dependencia
    @Autowired private CompanyServices companyServices;

    // HTTP GET - Lista todas as empresas
    @GetMapping
    public Page<CompanyDto> listAllCompanies(@PageableDefault(page = 0, size = 10,
                                                              direction = Sort.Direction.ASC, sort = "id")Pageable pagination) {
        return companyServices.listAllCompanies(pagination);
    }

    // HTTP GET - Puxa uma empresa pelo seu id
    @GetMapping("/id:{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        return companyServices.getCompanyById(id);
    }

    // HTTP GET - Puxa uma empresa pelo cnpj (APENAS NUMEROS)
    @GetMapping("/cnpj:{cnpj}")
    public ResponseEntity<CompanyDto> getCompanyByCnpj(@PathVariable String cnpj) {
        return companyServices.getCompanyByCnpj(cnpj);
    }

    // HTTP POST - Cadastra uma nova empresa
    @PostMapping @Transactional
    public ResponseEntity<CompanyDto> createCompany(@RequestBody @Valid CompanyForm form,
                                                    UriComponentsBuilder uriBuilder) {
        return companyServices.createCompany(form, uriBuilder);
    }

    // HTTP PUT - Atualiza todas as informações de uma empresa pelo seu id
    @PutMapping("/id:{id}") @Transactional
    public ResponseEntity<CompanyDto> updateCompanyById(@PathVariable Long id,
                                                        @RequestBody @Valid CompanyUpdate form) {
        return companyServices.updateCompanyById(id, form);
    }

    // HTTP DELETE - Deleta uma empresa pelo seu id
    @DeleteMapping("/id:{id}") @Transactional
    public ResponseEntity<?> deleteCompanyById(@PathVariable Long id) {
        return companyServices.deleteCompanyById(id);
    }
}
