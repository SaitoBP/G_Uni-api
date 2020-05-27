package br.com.g_uni.api.repository;

import br.com.g_uni.api.model.DataCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataCollectionRepository extends JpaRepository<DataCollection, Long> {
}
