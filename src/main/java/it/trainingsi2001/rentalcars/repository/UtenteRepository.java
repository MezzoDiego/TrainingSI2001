package it.trainingsi2001.rentalcars.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import it.trainingsi2001.rentalcars.entities.Ruolo;
import it.trainingsi2001.rentalcars.entities.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long>, CustomUtenteRepository{

	@EntityGraph(attributePaths = { "ruolo" })
	Optional<Utente> findByUsername(String username);

	Utente findByUsernameAndPassword(String username, String password);
	List<Utente> findByRuolo(Ruolo ruolo);
	
}
