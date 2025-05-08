package it.trainingsi2001.rentalcars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.trainingsi2001.rentalcars.entities.Ruolo;
import it.trainingsi2001.rentalcars.entities.StatoUtente;
import it.trainingsi2001.rentalcars.entities.Utente;
import it.trainingsi2001.rentalcars.repository.UtenteRepository;

@Service
@Transactional(readOnly = true)
public class UtenteServiceimpl implements UtenteService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Utente> listAll() {
        return utenteRepository.findAll();
    }

    @Override
    public Utente caricaSingoloElemento(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Utente aggiorna(Utente obj) {
		Utente utenteReloaded = utenteRepository.findByIdConRuolo(obj.getId()).orElse(null);
		if (utenteReloaded == null)
			throw new RuntimeException("Elemento non trovato");
		utenteReloaded.setNome(obj.getNome());
		utenteReloaded.setCognome(obj.getCognome());
		utenteReloaded.setDataDiNascita(obj.getDataDiNascita());
		utenteReloaded.setUsername(obj.getUsername());
		utenteReloaded.setRuolo(obj.getRuolo());
		return utenteRepository.save(utenteReloaded);
    }

    @Override
    @Transactional
    public Utente inserisciNuovo(Utente obj) {
        obj.setStato(StatoUtente.CREATO);
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        return utenteRepository.save(obj);
    }

    @Override
    @Transactional
    public void rimuovi(Long idToRemove) {
        utenteRepository.deleteById(idToRemove);
    }

    @Override
    @Transactional
    public void changeUserAbilitation(Long idUtente) throws RuntimeException {
        Utente utenteInstance = caricaSingoloElemento(idUtente);
        if (utenteInstance == null)
            throw new RuntimeException("Elemento non trovato.");

        if (utenteInstance.getStato() == null || utenteInstance.getStato().equals(StatoUtente.CREATO))
            utenteInstance.setStato(StatoUtente.ATTIVO);
        else if (utenteInstance.getStato().equals(StatoUtente.ATTIVO))
            utenteInstance.setStato(StatoUtente.DISABILITATO);
        else if (utenteInstance.getStato().equals(StatoUtente.DISABILITATO))
            utenteInstance.setStato(StatoUtente.ATTIVO);
    }

    @Override
    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<Utente> caricaListaUtentiByRuolo(Ruolo ruolo) {
        return utenteRepository.findByRuolo(ruolo);
    }

    @Override
    public Utente caricaUtenteConRuolo(Long id) {
        return utenteRepository.findByIdConRuolo(id).orElse(null);
    }

}
