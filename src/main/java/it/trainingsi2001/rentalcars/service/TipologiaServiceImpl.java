package it.trainingsi2001.rentalcars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.trainingsi2001.rentalcars.entities.Tipologia;
import it.trainingsi2001.rentalcars.repository.TipologiaRepository;

@Service
@Transactional(readOnly = true)
public class TipologiaServiceImpl implements TipologiaService{

    @Autowired
    TipologiaRepository tipologiaRepository;

    @Override
    public List<Tipologia> listAll() {
        return tipologiaRepository.findAll();
    }

    @Override
    public Tipologia caricaSingoloElemento(Long id) {
        return tipologiaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Tipologia aggiorna(Tipologia obj) {
        return tipologiaRepository.save(obj);
    }

    @Override
    @Transactional
    public Tipologia inserisciNuovo(Tipologia obj) {
        return tipologiaRepository.save(obj);
    }

    @Override
    @Transactional
    public void rimuovi(Long idToRemove) {
        tipologiaRepository.deleteById(idToRemove);
    }

}
