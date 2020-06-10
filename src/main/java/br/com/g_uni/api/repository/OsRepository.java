package br.com.g_uni.api.repository;

import br.com.g_uni.api.model.Os;
import br.com.g_uni.api.model.others.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OsRepository extends JpaRepository<Os, Long> {
    Page<Os> findByUo(Pageable pagination, Branch uo);
}
