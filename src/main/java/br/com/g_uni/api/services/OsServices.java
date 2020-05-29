package br.com.g_uni.api.services;

import br.com.g_uni.api.controller.dto.OsDto;
import br.com.g_uni.api.controller.form.OsForm;
import br.com.g_uni.api.controller.form.patch.OsPatchDocument;
import br.com.g_uni.api.model.Company;
import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.Os;
import br.com.g_uni.api.repository.CompanyRepository;
import br.com.g_uni.api.repository.DocumentRepository;
import br.com.g_uni.api.repository.OsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class OsServices {

    // Inejeção de dependencias
    @Autowired private OsRepository osRepository;
    @Autowired private CompanyRepository companyRepository;
    @Autowired private DocumentRepository documentRepository;

    // Método para listar todas as OS's
    public Page<OsDto> listAllOs(Pageable pagination) {
        Page<Os> os = osRepository.findAll(pagination);
        return OsDto.convert(os);
    }

    // Método para puxar uma OS pelo seu id
    public ResponseEntity<OsDto> getOsById(Long id) {
        Optional<Os> osId = osRepository.findById(id);
        return osId.map(os -> ResponseEntity.ok().body(new OsDto(os))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para criar uma nova OS
    public ResponseEntity<OsDto> createOs(OsForm form,
                                          UriComponentsBuilder uriBuilder) {
        // Verifica se o companyId existe no banco de dados
        Optional<Company> companyId = companyRepository.findById(form.getCompanyId());
        if (companyId.isPresent()) {
            // Converte: OsForm -> Os
            Os os = form.convert(companyRepository);

            // Salva no banco de dados
            osRepository.save(os);

            // Cria a uri
            URI uri = uriBuilder.path("/os/{id}").buildAndExpand(os.getId()).toUri();

            return ResponseEntity.created(uri).body(new OsDto(os));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para adicionar um documento na OS pelo seu id
    public ResponseEntity<OsDto> patchOsDocumentsById(Long id,
                                                      OsPatchDocument form) {
        Optional<Os> osId = osRepository.findById(id);
        if (osId.isPresent()) {
            Os os = osRepository.getOne(id);

            // Pega o documento de acordo com o id fornecido no form
            Document document = documentRepository.findById(form.getDocumentId()).get();

            os.getDocuments().add(document);
            return ResponseEntity.ok().body(new OsDto(os));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
