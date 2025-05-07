package it.trainingsi2001.rentalcars.service;

import java.util.List;

import it.trainingsi2001.rentalcars.entities.Ruolo;
import it.trainingsi2001.rentalcars.entities.Utente;

public interface UtenteService extends BaseService<Utente> {
    void changeUserAbilitation(Long idUtente);
    Utente findByUsername(String username);
    List<Utente> caricaListaUtentiByRuolo(Ruolo ruolo);
}
