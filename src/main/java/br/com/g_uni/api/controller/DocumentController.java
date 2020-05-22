package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.DocumentDto;
import br.com.g_uni.api.controller.form.DocumentForm;
import br.com.g_uni.api.controller.form.patch.DocumentPatchStatus;
import br.com.g_uni.api.controller.form.patch.DocumentPatchType;
import br.com.g_uni.api.controller.form.update.DocumentFormUpdate;
import br.com.g_uni.api.model.others.DocumentStatus;
import br.com.g_uni.api.model.others.DocumentType;
import br.com.g_uni.api.services.DocumentServices;
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

@RestController @RequestMapping("/document")
public class DocumentController {

    // Injeção de dependencias
    @Autowired private DocumentServices documentServices;

    // HTTP GET - Lista todos os documentos
    @GetMapping
    public Page<DocumentDto> listAllDocuments(@PageableDefault(page = 0, size = 10, sort = "id",
                                                               direction = Sort.Direction.ASC) Pageable pagination,
                                              @RequestParam(required = false) DocumentType docType,
                                              @RequestParam(required = false) DocumentStatus docStatus,
                                              @RequestParam(required = false) String auxiliar) {
        return documentServices.listAllDocuments(pagination, docType, docStatus, auxiliar);
    }

    // HTTP GET - Puxa um documento pelo id
    @GetMapping("/id:{id}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Long id) {
        return documentServices.getDocumentById(id);
    }

    // HTTP POST - Posta um novo documento
    @PostMapping @Transactional
    public ResponseEntity<DocumentDto> postDocument(@RequestBody @Valid DocumentForm documentForm,
                                                    UriComponentsBuilder uriBuilder) {
         return documentServices.createDocument(documentForm, uriBuilder
         );
    }

    // HTTP PUT - Atualiza todos os dados de um documento pelo seu id
    @PutMapping("/id:{id}") @Transactional
    public ResponseEntity<DocumentDto> updateDocumentById(@PathVariable Long id,
                                                          @RequestBody @Valid DocumentFormUpdate formUpdate) {
        return documentServices.updateDocumentById(id, formUpdate);
    }

    // HTTP PATCH - Atualiza o status do documento pelo seu id
    @PatchMapping("/status/id:{id}") @Transactional
    public ResponseEntity<DocumentDto> patchDocumentStatusById(@PathVariable Long id,
                                                               @RequestBody @Valid DocumentPatchStatus patchStatus) {
        return documentServices.patchDocumentStatusById(id, patchStatus);
    }

    // HTTP PATCH - Atualiza o tipo do documento pelo seu id
    @PatchMapping("/type/id:{id}") @Transactional
    public ResponseEntity<DocumentDto> patchDocumentTypeById(@PathVariable Long id,
                                                             @RequestBody DocumentPatchType patchType) {
        return documentServices.patchDocumentTypeById(id, patchType);
    }

    // HTTP DELETE - Deleta um documento pelo seu id
    @DeleteMapping("/id:{id}") @Transactional
    public ResponseEntity<?> deleteDocumentById(@PathVariable Long id) {
        return documentServices.deleteDocumentById(id);
    }
}
