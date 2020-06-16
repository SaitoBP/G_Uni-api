package br.com.g_uni.api.controller.form.update;

import br.com.g_uni.api.model.Document;
import br.com.g_uni.api.model.Os;
import br.com.g_uni.api.model.others.Branch;
import br.com.g_uni.api.repository.DocumentRepository;
import br.com.g_uni.api.repository.OsRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OsUpdate {

    // Atributos a serem atualizados:
    private String osNumber;
    private LocalDate osEmissionDate;
    private LocalDate osValidityDate;
    private Branch uo;
    private List<Long> documentsId;

    // Método para atualizar uma OS:
    public Os update(Long id, OsRepository osRepository, DocumentRepository documentRepository) {

        // Procura uma OS no banco de dados pelo seu id:
        Os os = osRepository.getOne(id);

        // Atualiza as informações vindas do banco de dados com as informações vindas do 'OsUpdate':
//        os.setOsNumber(this.osNumber);
//        os.setOsEmissionDate(this.osEmissionDate);
//        os.setOsValidityDate(this.osValidityDate);
//        os.setUo(this.uo);

        // Array de documentos a ser retornado:
        List<Document> documentList = new ArrayList<>();

        // Itera a lista de id's, procura os documentos no banco, e adiciona a lista de documentos da OS
        documentsId.forEach(docId -> {
            Optional<Document> document = documentRepository.findById(docId);
            document.ifPresent(documentList::add);
        });

        os.setDocuments(documentList);

        return os;
    }

    // Getters & Setters:
    public String getOsNumber() {
        return osNumber;
    }
    public void setOsNumber(String osNumber) {
        this.osNumber = osNumber;
    }
    public LocalDate getOsEmissionDate() {
        return osEmissionDate;
    }
    public void setOsEmissionDate(LocalDate osEmissionDate) {
        this.osEmissionDate = osEmissionDate;
    }
    public LocalDate getOsValidityDate() {
        return osValidityDate;
    }
    public void setOsValidityDate(LocalDate osValidityDate) {
        this.osValidityDate = osValidityDate;
    }
    public Branch getUo() {
        return uo;
    }
    public void setUo(Branch uo) {
        this.uo = uo;
    }
    public List<Long> getDocumentsId() {
        return documentsId;
    }
    public void setDocumentsId(List<Long> documentsId) {
        this.documentsId = documentsId;
    }
}
