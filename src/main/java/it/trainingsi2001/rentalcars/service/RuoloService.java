package it.trainingsi2001.rentalcars.service;

import it.trainingsi2001.rentalcars.entities.Ruolo;

public interface RuoloService extends BaseService<Ruolo>{

	Ruolo cercaPerDescrizione(String descrizione);

}
