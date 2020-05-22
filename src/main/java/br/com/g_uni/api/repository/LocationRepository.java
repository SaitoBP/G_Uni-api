package br.com.g_uni.api.repository;

import br.com.g_uni.api.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
