package br.com.g_uni.api.repository;

import br.com.g_uni.api.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepositoy extends JpaRepository<Information, Long> {
}
