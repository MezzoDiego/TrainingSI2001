package it.trainingsi2001.rentalcars;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.trainingsi2001.rentalcars.entities.Ruolo;
import it.trainingsi2001.rentalcars.entities.Utente;
import it.trainingsi2001.rentalcars.service.RuoloService;
import it.trainingsi2001.rentalcars.service.UtenteService;

@SpringBootApplication
public class RentalCarsApplication implements CommandLineRunner {

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private RuoloService ruoloService;

	public static void main(String[] args) {
		SpringApplication.run(RentalCarsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloService.cercaPerDescrizione(Ruolo.SUPER_ADMIN_ROLE) == null) {
			ruoloService.inserisciNuovo(Ruolo.builder().descrizione(Ruolo.SUPER_ADMIN_ROLE).build());
		}

		if (ruoloService.cercaPerDescrizione(Ruolo.CUSTOMER_ROLE) == null) {
			ruoloService.inserisciNuovo(Ruolo.builder().descrizione(Ruolo.CUSTOMER_ROLE).build());
		}

		if (utenteService.findByUsername("admin") == null) {

			Utente superAdmin = Utente.builder()
			.nome("Diego")
			.cognome("Mezzo")
			.username("admin")
			.password("admin")
			.dataDiNascita(LocalDate.of(2002, 10, 1))
			.ruolo(ruoloService.cercaPerDescrizione(Ruolo.SUPER_ADMIN_ROLE))
			.build();
			
			utenteService.inserisciNuovo(superAdmin);
			utenteService.changeUserAbilitation(superAdmin.getId());
		}

		if (utenteService.findByUsername("customer") == null) {

			Utente customer = Utente.builder()
			.nome("Luigi")
			.cognome("Durso")
			.username("customer")
			.password("customer")
			.dataDiNascita(LocalDate.of(1995, 01, 1))
			.ruolo(ruoloService.cercaPerDescrizione(Ruolo.CUSTOMER_ROLE))
			.build();
			
			utenteService.inserisciNuovo(customer);
			utenteService.changeUserAbilitation(customer.getId());
		}

	}

}
