package it.trainingsi2001.rentalcars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.trainingsi2001.rentalcars.entities.Ruolo;
import it.trainingsi2001.rentalcars.repository.RuoloRepository;

@Service
@Transactional(readOnly = true)
public class RuoloServiceImpl implements RuoloService{

	@Autowired
	private RuoloRepository ruoloRepository;

    @Override
    public List<Ruolo> listAll() {
        return ruoloRepository.findAll();
    }

    @Override
    public Ruolo caricaSingoloElemento(Long id) {
        return ruoloRepository.findById(id).orElse(null);

    }

    @Override
    @Transactional
    public Ruolo aggiorna(Ruolo ruoloInstance) {
        return ruoloRepository.save(ruoloInstance);
    }

    @Override
    @Transactional
    public Ruolo inserisciNuovo(Ruolo ruoloInstance) {
        return ruoloRepository.save(ruoloInstance);
    }

    @Override
    @Transactional
    public void rimuovi(Long idToRemove) {
        ruoloRepository.deleteById(idToRemove);
    }

    @Override
    public Ruolo cercaPerDescrizione(String descrizione) {
        return ruoloRepository.findByDescrizione(descrizione);
    }

}
