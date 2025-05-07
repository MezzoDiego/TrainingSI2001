package it.trainingsi2001.rentalcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.trainingsi2001.rentalcars.entities.Ruolo;


public interface RuoloRepository extends JpaRepository<Ruolo, Long>{
	Ruolo findByDescrizione(String descrizione);

}
