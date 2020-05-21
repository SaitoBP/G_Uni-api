package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.DocumentDto;
import br.com.g_uni.api.controller.form.DocumentForm;
import br.com.g_uni.api.controller.form.patch.DocumentFormPatch;
import br.com.g_uni.api.controller.form.update.DocumentFormUpdate;
import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentStatus;
import br.com.g_uni.api.model.others.DocumentType;
import br.com.g_uni.api.repository.DocumentRepository;
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
import java.net.URI;
import java.util.Optional;

@RestController @RequestMapping("/document")
public class DocumentController {

    // Injeção de dependencias
    @Autowired private DocumentRepository documentRepository;


    // HTTP GET - Lista todos os documentos
    @GetMapping
    public Page<DocumentDto> listAllDocuments(@PageableDefault(page = 0, size = 10, sort = "id",
                                                               direction = Sort.Direction.ASC) Pageable pagination,
                                              @RequestParam(required = false) DocumentType docType,
                                              @RequestParam(required = false) DocumentStatus docStatus,
                                              @RequestParam(required = false) String auxiliar) {
        if (docType != null) {
            Page<Document> documents = documentRepository.findByDocType(docType, pagination);
            return DocumentDto.convert(documents);
        } else if (docStatus != null) {
            Page<Document> documents = documentRepository.findByDocStatus(docStatus, pagination);
            return DocumentDto.convert(documents);
        } else if (auxiliar != null){
            Page<Document> documents = documentRepository.findByAuxiliar(auxiliar, pagination);
            return DocumentDto.convert(documents);
        } else {
            Page<Document> documents = documentRepository.findAll(pagination);
            return DocumentDto.convert(documents);
        }
    }

    // HTTP GET - Puxa um documento pelo id
    @GetMapping("/id:{id}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Long id) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        return documentOptional.map(document -> ResponseEntity.ok(new DocumentDto(document))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // HTTP POST - Posta um novo documento
    @PostMapping @Transactional
    public ResponseEntity<DocumentDto> postDocument(@RequestBody @Valid DocumentForm documentForm,
                                                    UriComponentsBuilder uriBuilder) {
        // ToDo Fazer com que o nome do auxiliar seja pego do banco de dados
        Document document = documentForm.convert();

        // Salva o documento no banco de dados
        documentRepository.save(document);
        URI uri = uriBuilder.path("/document/{id}").buildAndExpand(document.getId()).toUri();

        return ResponseEntity.created(uri).body(new DocumentDto(document));
    }

    // HTTP PUT - Atualiza todos os dados de um documento pelo seu id
    @PutMapping("/id:{id}") @Transactional
    public ResponseEntity<DocumentDto> updateDocumentById(@PathVariable Long id,
                                                          @RequestBody @Valid DocumentFormUpdate formUpdate) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            Document document = formUpdate.update(id, documentRepository);
            return ResponseEntity.ok(new DocumentDto(document));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP PATCH - Atualiza o status do documento pelo seu id
    @PatchMapping("/status/id:{id}") @Transactional
    public ResponseEntity<DocumentDto> patchDocumentStatusById(@PathVariable Long id,
                                                               @RequestBody DocumentFormPatch formPatch) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            // Procura um documento no banco de dados pelo seu id
            Document document = formPatch.patchStatus(id, documentRepository);
            return ResponseEntity.ok(new DocumentDto(document));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP PATCH - Atualiza o tipo do documento pelo seu id
    @PatchMapping("/type/id:{id}") @Transactional
    public ResponseEntity<DocumentDto> patchDocumentTypeById(@PathVariable Long id,
                                                             @RequestBody DocumentFormPatch formPatch) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            // Procura um documento no banco de dados pelo seu id
            Document document = formPatch.patchType(id, documentRepository);
            return ResponseEntity.ok(new DocumentDto(document));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP PATCH - Atualiza o auxiliar do documento pelo seu id
        // ToDo - Fazer com que o auxiliar seja pego do banco de dados
    @PatchMapping("/auxiliar/id:{id}") @Transactional
    public ResponseEntity<DocumentDto> patchDocumentAuxiliarById(@PathVariable Long id,
                                                                 @RequestBody DocumentFormPatch formPatch) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            // Procura um documento no banco de dados pelo seu id
            Document document = formPatch.patchAuxiliar(id, documentRepository);
            return ResponseEntity.ok(new DocumentDto(document));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP PATCH - Adiciona data de finalização do documento pelo seu id
    @PatchMapping("/finish/id:{id}") @Transactional
    public ResponseEntity<DocumentDto> patchDocumentFinishDateById(@PathVariable Long id,
                                                                   @RequestBody DocumentFormPatch formPatch) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            // Procura um documento no banco de dados pelo seu id
            Document document = formPatch.patchFinishDate(id, documentRepository);
            return ResponseEntity.ok(new DocumentDto(document));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP PATCH - Adiciona data de envio para validação do documento pelo seu id
    @PatchMapping("/validation/id:{id}") @Transactional
    public ResponseEntity<DocumentDto> patchDocumentValidationDateById(@PathVariable Long id,
                                                                       @RequestBody DocumentFormPatch formPatch) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            // Procura um documento no banco de dados pelo seu id
            Document document = formPatch.patchValidationDate(id, documentRepository);
            return ResponseEntity.ok(new DocumentDto(document));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP DELETE - Deleta um documento pelo seu id
    @DeleteMapping("/id:{id}") @Transactional
    public ResponseEntity<?> deleteDocumentById(@PathVariable Long id) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            documentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
