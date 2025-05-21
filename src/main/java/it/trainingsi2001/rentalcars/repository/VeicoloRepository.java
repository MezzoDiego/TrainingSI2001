package it.trainingsi2001.rentalcars.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.trainingsi2001.rentalcars.entities.Veicolo;

public interface VeicoloRepository extends JpaRepository<Veicolo, Long> {
    @Query("SELECT v FROM Veicolo v JOIN FETCH v.tipologia WHERE v.id = :id")
    Optional<Veicolo> findByIdFetchTipologia(Long id);

     boolean existsByTipologia_Id(Long tipologiaId);
}
