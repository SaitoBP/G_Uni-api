package br.com.g_uni.api.services;

import br.com.g_uni.api.controller.dto.CompanyDto;
import br.com.g_uni.api.controller.form.CompanyForm;
import br.com.g_uni.api.controller.form.update.CompanyUpdate;
import br.com.g_uni.api.model.Company;
import br.com.g_uni.api.model.Information;
import br.com.g_uni.api.model.Location;
import br.com.g_uni.api.repository.CompanyRepository;
import br.com.g_uni.api.repository.InformationRepositoy;
import br.com.g_uni.api.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.attribute.ResolutionSyntax;
import java.net.URI;
import java.util.Optional;

@Service
public class CompanyServices {

    // Injeção de dependencias
    @Autowired private CompanyRepository companyRepository;
    @Autowired private LocationRepository locationRepository;
    @Autowired private InformationRepositoy informationRepositoy;

    // Método para listar todas as empresas
    public Page<CompanyDto> listAllCompanies(Pageable pagination) {
        Page<Company> companies = companyRepository.findAll(pagination);
        return CompanyDto.convert(companies);
    }

    // Método para puxar uma empresa pelo seu id
    public ResponseEntity<CompanyDto> getCompanyById(Long id) {
        Optional<Company> byId = companyRepository.findById(id);
        return byId.map(company -> ResponseEntity.ok().body(new CompanyDto(company))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para puxar uma empresa pelo seu cnpj
    public ResponseEntity<CompanyDto> getCompanyByCnpj(String cnpj) {
        Optional<Company> companyCnpj = companyRepository.findByCnpj(cnpj);
        return companyCnpj.map(company -> ResponseEntity.ok(new CompanyDto(company))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para cadastrar uma nova empresa
    public ResponseEntity<CompanyDto> createCompany(CompanyForm form,
                                                    UriComponentsBuilder uriBuilder) {
        // Verifica se o locationId e o informationId estão no banco de dados
        Optional<Location> locationId = locationRepository.findById(form.getLocationId());
        Optional<Information> informationId = informationRepositoy.findById(form.getInformationId());

        if (locationId.isPresent() && informationId.isPresent()) {
            // Converte: CompanyForm -> Company
            Company company = form.convert(locationRepository,
                                           informationRepositoy);

            // Salva a empresa no banco de dados
            companyRepository.save(company);

            // Cria a uri
            URI uri = uriBuilder.path("/company/{id}").buildAndExpand(company.getId()).toUri();

            return ResponseEntity.created(uri).body(new CompanyDto(company));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para atualizar todas as informações de uma empresa
    public ResponseEntity<CompanyDto> updateCompanyById(Long id, CompanyUpdate form) {
        // Verifica se o id da empresa fornecido existe no banco de dados
        Optional<Company> companyId = companyRepository.findById(id);

        if (companyId.isPresent()) {
            // Verifica se o locationId e informationId fornecidos existem no banco de dados
            Optional<Location> locationId = locationRepository.findById(form.getLocationId());
            Optional<Information> informationId = informationRepositoy.findById(form.getInformationId());

            if (locationId.isPresent() && informationId.isPresent()) {
                Company company = form.update(id, companyRepository, locationRepository, informationRepositoy);
                return ResponseEntity.ok().body(new CompanyDto(company));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar uma empresa pelo seu id
    public ResponseEntity<?> deleteCompanyById(Long id) {
        Optional<Company> companyId = companyRepository.findById(id);
        if (companyId.isPresent()) {
            companyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
