package br.com.g_uni.api.services;

import br.com.g_uni.api.controller.dto.DocumentDto;
import br.com.g_uni.api.controller.form.DocumentForm;
import br.com.g_uni.api.controller.form.patch.DocumentPatchStatus;
import br.com.g_uni.api.controller.form.patch.DocumentPatchType;
import br.com.g_uni.api.controller.form.update.DocumentUpdate;
import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentStatus;
import br.com.g_uni.api.model.others.DocumentType;
import br.com.g_uni.api.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class DocumentServices {

    // Injeção de dependencias
    @Autowired private DocumentRepository documentRepository;

    // Método para listar todos os documentos
    public Page<DocumentDto> listAllDocuments(Pageable pagination,
                                              DocumentType docType,
                                              DocumentStatus docStatus,
                                              String auxiliar) {
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

    // Método para puxar um documento pelo seu id
    public ResponseEntity<DocumentDto> getDocumentById(Long id) {
        Optional<Document> byId = documentRepository.findById(id);
        return byId.map(document -> ResponseEntity.ok(new DocumentDto(document))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para cadastrar um novo documento
    public ResponseEntity<DocumentDto> createDocument(DocumentForm form,
                                                      UriComponentsBuilder uriBuilder) {
        // Converte: DocumentForm -> Document
        Document document = form.convert();

        // Salva o documento no banco de dados
        documentRepository.save(document);

        // Cria a uri
        URI uri = uriBuilder.path("/document/{id}").buildAndExpand(document.getId()).toUri();

        return ResponseEntity.created(uri).body(new DocumentDto(document));
    }
    // Método para atualizar todos os dados de um documento pelo seu id
    public ResponseEntity<DocumentDto> updateDocumentById(Long id, DocumentUpdate form) {
        Optional<Document> byId = documentRepository.findById(id);
        if (byId.isPresent()) {
            Document document = form.update(id, documentRepository);
            return ResponseEntity.ok(new DocumentDto(document));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para atualizar o status do documento pelo seu id
    public ResponseEntity<DocumentDto> patchDocumentStatusById(Long id, DocumentPatchStatus patchStatus) {
        Optional<Document> byId = documentRepository.findById(id);
        if (byId.isPresent()) {
            Document document = documentRepository.getOne(id);
            document.setDocStatus(patchStatus.getDocumentStatus());
            return ResponseEntity.ok(new DocumentDto(document));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para atualizar o tipo de documento pelo seu id
    public ResponseEntity<DocumentDto> patchDocumentTypeById(Long id, DocumentPatchType patchType) {
        Optional<Document> byId = documentRepository.findById(id);
        if (byId.isPresent()) {
            Document document = documentRepository.getOne(id);
            document.setDocType(patchType.getDocumentType());
            return ResponseEntity.ok(new DocumentDto(document));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar um documento pelo seu id
    public ResponseEntity<?> deleteDocumentById(Long id) {
        Optional<Document> byId = documentRepository.findById(id);
        if (byId.isPresent()) {
            documentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
