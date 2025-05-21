package it.trainingsi2001.rentalcars.service;

import it.trainingsi2001.rentalcars.entities.Veicolo;

public interface VeicoloService extends BaseService<Veicolo>{
   Veicolo caricaSingoloElementoConTipologia(Long id);
}
