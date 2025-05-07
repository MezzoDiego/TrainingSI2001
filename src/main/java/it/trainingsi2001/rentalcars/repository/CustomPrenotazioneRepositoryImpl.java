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
    public List<Prenotazione> findByExample(PrenotazioneExampleDTO example) {

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
    if (StringUtils.isNotBlank(example.getNomeUtente())) {
        predicates.add(cb.like(cb.lower(utenteJoin.get("nome")), "%" + example.getNomeUtente().toLowerCase() + "%"));
    }
    if (StringUtils.isNotBlank(example.getCognomeUtente())) {
        predicates.add(cb.like(cb.lower(utenteJoin.get("cognome")), "%" + example.getCognomeUtente().toLowerCase() + "%"));
    }
    if (StringUtils.isNotBlank(example.getDataDiNascitaUtente())) {
        predicates.add(cb.like(cb.lower(utenteJoin.get("dataDiNascita")), "%" + example.getDataDiNascitaUtente().toLowerCase() + "%"));
    }
    if (StringUtils.isNotBlank(example.getStatoUtente())) {
        predicates.add(cb.equal(utenteJoin.get("stato"), example.getStatoUtente()));
    }

    // Filtro Veicolo
    if (StringUtils.isNotBlank(example.getModelloVeicolo())) {
        predicates.add(cb.like(cb.lower(veicoloJoin.get("modello")), "%" + example.getModelloVeicolo().toLowerCase() + "%"));
    }
    if (StringUtils.isNotBlank(example.getTargaVeicolo())) {
        predicates.add(cb.like(cb.lower(veicoloJoin.get("targa")), "%" + example.getTargaVeicolo().toLowerCase() + "%"));
    }
    if (StringUtils.isNotBlank(example.getAlimentazioneVeicolo())) {
        predicates.add(cb.like(cb.lower(veicoloJoin.get("alimentazione")), "%" + example.getAlimentazioneVeicolo().toLowerCase() + "%"));
    }
    if (StringUtils.isNotBlank(example.getCasaCostruttriceVeicolo())) {
        predicates.add(cb.like(cb.lower(veicoloJoin.get("casaCostruttrice")), "%" + example.getCasaCostruttriceVeicolo().toLowerCase() + "%"));
    }

    // Tipologia
    if (StringUtils.isNotBlank(example.getTipologiaVeicolo())) {
        predicates.add(cb.like(cb.lower(tipologiaJoin.get("descrizione")), "%" + example.getTipologiaVeicolo().toLowerCase() + "%"));
    }

    // Date
    if (example.getDataInizioPrenotazione() != null) {
        predicates.add(cb.greaterThanOrEqualTo(root.get("dataInizio"), example.getDataInizioPrenotazione()));
    }
    if (example.getDataFinePrenotazione() != null) {
        predicates.add(cb.lessThanOrEqualTo(root.get("dataFine"), example.getDataFinePrenotazione()));
    }

    query.where(cb.and(predicates.toArray(new Predicate[0])));
    return entityManager.createQuery(query).getResultList();
    }

    

}
