package it.trainingsi2001.rentalcars.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.trainingsi2001.rentalcars.entities.Prenotazione;
import it.trainingsi2001.rentalcars.entities.Utente;
import it.trainingsi2001.rentalcars.entities.Veicolo;
import it.trainingsi2001.rentalcars.repository.PrenotazioneRepository;
import it.trainingsi2001.rentalcars.repository.UtenteRepository;
import it.trainingsi2001.rentalcars.repository.VeicoloRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class PrenotazioneServiceImpl implements PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    VeicoloRepository veicoloRepository;

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
    public Prenotazione cambiaStatoPrenotazione(Long idPrenotazione, Boolean flagApprovazione) {
        prenotazioneRepository.updateFlagApprovazioneById(idPrenotazione, flagApprovazione);
        return this.caricaSingoloElemento(idPrenotazione);
    }

    @Override
    @Transactional
    public void cancellaPrenotazioneByIdInTempo(Long id) {
        prenotazioneRepository.deleteByIdIfAtLeastTwoDaysBefore(id, LocalDateTime.now().plusDays(2));
    }

    @Override
    @Transactional
    public Prenotazione modificaPrenotazioneInTempo(Prenotazione prenotazioneItem) {
        Prenotazione prenotazioneReloaded = prenotazioneRepository.findById(prenotazioneItem.getId())
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));

        if (prenotazioneReloaded.getDataInizio().isBefore(LocalDateTime.now().plusDays(2))) {
            throw new IllegalStateException(
                    "La prenotazione non può essere modificata: mancano meno di 2 giorni all'inizio.");
        }

        prenotazioneReloaded.setDataInizio(prenotazioneItem.getDataInizio());
        prenotazioneReloaded.setDataFine(prenotazioneItem.getDataFine());

        Veicolo veicolo = veicoloRepository.findById(prenotazioneItem.getVeicolo().getId())
                .orElseThrow(() -> new EntityNotFoundException("Veicolo non trovato"));
        prenotazioneReloaded.setVeicolo(veicolo);

        return prenotazioneRepository.save(prenotazioneReloaded);
    }

    @Override
    public List<Prenotazione> caricaPrenotazioniByFilters(Prenotazione prenotazioneItem) {

        return prenotazioneRepository.findByExample(prenotazioneItem);

    }

    @Override
    public Prenotazione caricaSingoloElementoConVeicoloEUtente(Long id) {
        return prenotazioneRepository.findByIdFetchVeicoloAndUtente(id)
                .orElseThrow(() -> new EntityNotFoundException("prenotazione non trovata"));
    }

}
