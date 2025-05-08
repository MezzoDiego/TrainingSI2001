package it.trainingsi2001.rentalcars.service;

import java.util.List;

import it.trainingsi2001.rentalcars.entities.Prenotazione;
import it.trainingsi2001.rentalcars.entities.Utente;

public interface PrenotazioneService extends BaseService<Prenotazione>{
    List<Prenotazione> caricaListaPrenotazioniByUtente(Utente utente);
    int cambiaStatoPrenotazione(Long idPrenotazione, Boolean flagApprovazione);
    void cancellaPrenotazioneByIdInTempo(Long id);
    Prenotazione modificaPrenotazioneInTempo(Prenotazione prenotazioneItem);
    List<Prenotazione> caricaPrenotazioniByFilters(Prenotazione prenotazioneItem);
}
