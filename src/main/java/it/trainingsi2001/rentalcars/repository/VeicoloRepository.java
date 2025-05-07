package it.trainingsi2001.rentalcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.trainingsi2001.rentalcars.entities.Veicolo;

public interface VeicoloRepository extends JpaRepository<Veicolo, Long>{

}
