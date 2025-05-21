package it.trainingsi2001.rentalcars.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.trainingsi2001.rentalcars.entities.Prenotazione;
import it.trainingsi2001.rentalcars.entities.Utente;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long>, CustomPrenotazioneRepository {
    List<Prenotazione> findByUtente(Utente utente);

    @Modifying
    @Query("UPDATE Prenotazione p SET p.flagApprovazione = :flagApprovazione WHERE p.id = :id")
    int updateFlagApprovazioneById(@Param("id") Long id, @Param("flagApprovazione") boolean flagApprovazione);

    @Modifying
    @Query("DELETE FROM Prenotazione p WHERE p.id = :id AND p.dataInizio >= :limitDate")
    void deleteByIdIfAtLeastTwoDaysBefore(@Param("id") Long id, @Param("limitDate") LocalDateTime limitDate);

    @Query("SELECT p FROM Prenotazione p JOIN FETCH p.veicolo JOIN FETCH p.utente WHERE p.id = :id")
    Optional<Prenotazione> findByIdFetchVeicoloAndUtente(@Param("id") Long id);

}
