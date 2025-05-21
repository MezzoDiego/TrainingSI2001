package it.trainingsi2001.rentalcars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.trainingsi2001.rentalcars.entities.Tipologia;
import it.trainingsi2001.rentalcars.repository.TipologiaRepository;
import it.trainingsi2001.rentalcars.repository.VeicoloRepository;

@Service
@Transactional(readOnly = true)
public class TipologiaServiceImpl implements TipologiaService {

    @Autowired
    TipologiaRepository tipologiaRepository;

    @Autowired
    VeicoloRepository veicoloRepository;

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
        if (veicoloRepository.existsByTipologia_Id(idToRemove)) {
            throw new IllegalStateException("Impossibile eliminare: ci sono veicoli associati a questa tipologia.");
        }
        tipologiaRepository.deleteById(idToRemove);
    }

}
