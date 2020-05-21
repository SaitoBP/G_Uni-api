package br.com.g_uni.api.repository;

import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.others.DocumentStatus;
import br.com.g_uni.api.model.others.DocumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Page<Document> findByDocStatus(DocumentStatus documentType, Pageable pagination);

    Page<Document> findByDocType(DocumentType docType, Pageable pagination);

    Page<Document> findByAuxiliar(String auxiliar, Pageable pagination);
}
