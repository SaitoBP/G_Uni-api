package br.com.g_uni.api.repository;

import br.com.g_uni.api.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
