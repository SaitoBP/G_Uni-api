package br.com.g_uni.api.repository;

import br.com.g_uni.api.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
