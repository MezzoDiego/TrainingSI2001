package it.trainingsi2001.rentalcars.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.trainingsi2001.rentalcars.dto.PrenotazioneExampleDTO;
import it.trainingsi2001.rentalcars.entities.Prenotazione;
import it.trainingsi2001.rentalcars.entities.Ruolo;
import it.trainingsi2001.rentalcars.entities.Tipologia;
import it.trainingsi2001.rentalcars.entities.Utente;
import it.trainingsi2001.rentalcars.entities.Veicolo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CustomPrenotazioneRepositoryImpl implements CustomPrenotazioneRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Prenotazione> findByExample(Prenotazione example) {

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Prenotazione> query = cb.createQuery(Prenotazione.class);
    Root<Prenotazione> root = query.from(Prenotazione.class);

    // Join a entità correlate
    Join<Prenotazione, Utente> utenteJoin = root.join("utente", JoinType.LEFT);
    Join<Prenotazione, Veicolo> veicoloJoin = root.join("veicolo", JoinType.LEFT);
    Join<Utente, Ruolo> ruoloJoin = utenteJoin.join("ruolo", JoinType.LEFT);
    Join<Veicolo, Tipologia> tipologiaJoin = veicoloJoin.join("tipologia", JoinType.LEFT);

    List<Predicate> predicates = new ArrayList<>();

    // Filtro Utente
    if (example.getUtente() != null && example.getUtente().getNome() != null && StringUtils.isNotBlank(example.getUtente().getNome())) {
        predicates.add(cb.like(cb.lower(utenteJoin.get("nome")), "%" + example.getUtente().getNome().toLowerCase() + "%"));
    }
    if (example.getUtente() != null && example.getUtente().getCognome() != null && StringUtils.isNotBlank(example.getUtente().getCognome())) {
        predicates.add(cb.like(cb.lower(utenteJoin.get("cognome")), "%" + example.getUtente().getCognome().toLowerCase() + "%"));
    }
    if (example.getUtente() != null && example.getUtente().getDataDiNascita() != null) {
        predicates.add(cb.equal(utenteJoin.get("dataDiNascita"), example.getUtente().getDataDiNascita()));
    }
    if (example.getUtente() != null && example.getUtente().getStato() != null) {
        predicates.add(cb.equal(utenteJoin.get("stato"), example.getUtente().getStato()));
    }

    // Filtro Veicolo
    if (example.getVeicolo() != null && example.getVeicolo().getModello() != null && StringUtils.isNotBlank(example.getVeicolo().getModello())) {
        predicates.add(cb.like(cb.lower(veicoloJoin.get("modello")), "%" + example.getVeicolo().getModello().toLowerCase() + "%"));
    }
    if (example.getVeicolo() != null && example.getVeicolo().getTarga() != null && StringUtils.isNotBlank(example.getVeicolo().getTarga())) {
        predicates.add(cb.like(cb.lower(veicoloJoin.get("targa")), "%" + example.getVeicolo().getTarga().toLowerCase() + "%"));
    }
    if (example.getVeicolo() != null && example.getVeicolo().getAlimentazione() != null && StringUtils.isNotBlank(example.getVeicolo().getAlimentazione())) {
        predicates.add(cb.like(cb.lower(veicoloJoin.get("alimentazione")), "%" + example.getVeicolo().getAlimentazione().toLowerCase() + "%"));
    }
    if (example.getVeicolo() != null && example.getVeicolo().getCasaCostruttrice() != null && StringUtils.isNotBlank(example.getVeicolo().getCasaCostruttrice())) {
        predicates.add(cb.like(cb.lower(veicoloJoin.get("casaCostruttrice")), "%" + example.getVeicolo().getCasaCostruttrice().toLowerCase() + "%"));
    }

    // Tipologia
    if (example.getVeicolo() != null && example.getVeicolo().getTipologia() != null) {
        predicates.add(cb.equal(tipologiaJoin.get("id"), example.getVeicolo().getTipologia().getId()));
    }

    // Date
    if (example.getDataInizio() != null) {
        predicates.add(cb.greaterThanOrEqualTo(root.get("dataInizio"), example.getDataInizio()));
    }
    if (example.getDataFine() != null) {
        predicates.add(cb.lessThanOrEqualTo(root.get("dataFine"), example.getDataFine()));
    }

    if(example.getFlagApprovazione() != null){
        predicates.add(cb.equal(root.get("flagApprovazione"), example.getFlagApprovazione()));
    }

    query.where(cb.and(predicates.toArray(new Predicate[0])));
    return entityManager.createQuery(query).getResultList();
    }

    

}
