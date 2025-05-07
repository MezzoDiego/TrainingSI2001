package it.trainingsi2001.rentalcars.repository;

import java.util.List;

import it.trainingsi2001.rentalcars.dto.PrenotazioneExampleDTO;
import it.trainingsi2001.rentalcars.entities.Prenotazione;


public interface CustomPrenotazioneRepository {
    List<Prenotazione> findByExample(PrenotazioneExampleDTO example);

}
