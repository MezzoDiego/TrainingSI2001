package it.trainingsi2001.rentalcars.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.trainingsi2001.rentalcars.entities.Prenotazione;
import it.trainingsi2001.rentalcars.entities.Tipologia;
import it.trainingsi2001.rentalcars.entities.Veicolo;
import it.trainingsi2001.rentalcars.repository.TipologiaRepository;
import it.trainingsi2001.rentalcars.repository.VeicoloRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class VeicoloServiceImpl implements VeicoloService {

    @Autowired
    VeicoloRepository veicoloRepository;

    @Autowired
    TipologiaRepository tipologiaRepository;

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
    public Veicolo aggiorna(Veicolo veicoloItem) {
        Veicolo veicoloReloaded = veicoloRepository.findById(veicoloItem.getId())
                .orElseThrow(() -> new EntityNotFoundException("Veicolo non trovato"));

        veicoloReloaded.setCasaCostruttrice(veicoloItem.getCasaCostruttrice());
        veicoloReloaded.setModello(veicoloItem.getModello());
        veicoloReloaded.setAnnoImmatricolazione(veicoloItem.getAnnoImmatricolazione());
        veicoloReloaded.setTarga(veicoloItem.getTarga());
        veicoloReloaded.setCilindrata(veicoloItem.getCilindrata());
        veicoloReloaded.setEmissioni(veicoloItem.getEmissioni());
        veicoloReloaded.setPotenza(veicoloItem.getPotenza());
        veicoloReloaded.setNumeroTelaio(veicoloItem.getNumeroTelaio());
        veicoloReloaded.setKilometraggio(veicoloItem.getKilometraggio());
        veicoloReloaded.setConsumoMedioCarburanteUrbano(veicoloItem.getConsumoMedioCarburanteUrbano());
        veicoloReloaded.setConsumoMedioCarburanteExtraurbano(veicoloItem.getConsumoMedioCarburanteExtraurbano());
        veicoloReloaded.setAlimentazione(veicoloItem.getAlimentazione());

        if (veicoloItem.getTipologia() != null && veicoloItem.getTipologia().getId() != null) {
            Tipologia tipologia = tipologiaRepository.findById(veicoloItem.getTipologia().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Tipologia non trovata"));
            veicoloReloaded.setTipologia(tipologia);
        }

        return veicoloRepository.save(veicoloReloaded);

    }

    @Override
    @Transactional
    public Veicolo inserisciNuovo(Veicolo veicoloItem) {
        return veicoloRepository.save(veicoloItem);
    }

    @Override
    @Transactional
    public void rimuovi(Long idToRemove) {
        veicoloRepository.deleteById(idToRemove);
    }

    @Override
    public Veicolo caricaSingoloElementoConTipologia(Long id) {
        return veicoloRepository.findByIdFetchTipologia(id)
                .orElseThrow(() -> new EntityNotFoundException("Veicolo non trovato"));
    }

}
