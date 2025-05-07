package it.trainingsi2001.rentalcars.repository;

import java.util.List;

import it.trainingsi2001.rentalcars.entities.Utente;

public interface CustomUtenteRepository {
    List<Utente> findByExample(Utente example);
}
