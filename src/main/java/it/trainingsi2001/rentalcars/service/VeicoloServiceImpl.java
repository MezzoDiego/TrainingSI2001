package it.trainingsi2001.rentalcars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.trainingsi2001.rentalcars.entities.Veicolo;
import it.trainingsi2001.rentalcars.repository.VeicoloRepository;

@Service
@Transactional(readOnly = true)
public class VeicoloServiceImpl implements VeicoloService{

    @Autowired
    VeicoloRepository veicoloRepository;

    @Override
    public List<Veicolo> listAll() {
        return veicoloRepository.findAll();
    }

    @Override
    public Veicolo caricaSingoloElemento(Long id) {
        return veicoloRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Veicolo aggiorna(Veicolo obj) {
        return veicoloRepository.save(obj);
    }

    @Override
    @Transactional
    public Veicolo inserisciNuovo(Veicolo obj) {
        return veicoloRepository.save(obj);
    }

    @Override
    @Transactional
    public void rimuovi(Long idToRemove) {
        veicoloRepository.deleteById(idToRemove);
    }

}
