package br.com.g_uni.api.repository;

import br.com.g_uni.api.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
