package it.trainingsi2001.rentalcars.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.trainingsi2001.rentalcars.entities.Prenotazione;
import it.trainingsi2001.rentalcars.entities.Utente;
import it.trainingsi2001.rentalcars.repository.PrenotazioneRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class PrenotazioneServiceImpl implements PrenotazioneService{

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    @Override
    public List<Prenotazione> listAll() {
        return prenotazioneRepository.findAll();
    }

    @Override
    public Prenotazione caricaSingoloElemento(Long id) {
        return prenotazioneRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Prenotazione aggiorna(Prenotazione obj) {
        return prenotazioneRepository.save(obj);
    }

    @Override
    @Transactional
    public Prenotazione inserisciNuovo(Prenotazione obj) {
        return prenotazioneRepository.save(obj);
    }

    @Override
    @Transactional
    public void rimuovi(Long idToRemove) {
        prenotazioneRepository.deleteById(idToRemove);
    }

    @Override
    public List<Prenotazione> caricaListaPrenotazioniByUtente(Utente utente) {
        return prenotazioneRepository.findByUtente(utente);
    }

    @Override
    @Transactional
    public int cambiaStatoPrenotazione(Long idPrenotazione, boolean flagApprovazione) {
        return prenotazioneRepository.updateflagApprovazioneById(idPrenotazione, flagApprovazione);
    }

    @Override
    @Transactional
    public void cancellaPrenotazioneByIdInTempo(Long id) {
        prenotazioneRepository.deleteByIdIfAtLeastTwoDaysBefore(id, LocalDateTime.now().plusDays(2));
    }

    @Override
    @Transactional
    public Prenotazione modificaPrenotazioneInTempo(Prenotazione prenotazioneItem) {
        Prenotazione prenotazioneReloadedById = prenotazioneRepository.findById(prenotazioneItem.getId())
            .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));

    if (prenotazioneReloadedById.getDataInizio().isBefore(LocalDateTime.now().plusDays(2))) {
        throw new IllegalStateException("La prenotazione non può essere modificata: mancano meno di 2 giorni all'inizio.");
    }

    return prenotazioneRepository.save(prenotazioneItem);
    }

}
