package it.trainingsi2001.rentalcars.service;

import it.trainingsi2001.rentalcars.entities.Utente;

public interface UtenteService extends BaseService<Utente> {
    void changeUserAbilitation(Long idUtente);
    Utente findByUsername(String username);
}
