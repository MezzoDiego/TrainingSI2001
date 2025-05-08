package it.trainingsi2001.rentalcars.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trainingsi2001.rentalcars.dto.PrenotazioneDTO;
import it.trainingsi2001.rentalcars.dto.mapper.PrenotazioneMapper;
import it.trainingsi2001.rentalcars.entities.Prenotazione;
import it.trainingsi2001.rentalcars.service.PrenotazioneService;
import it.trainingsi2001.rentalcars.service.UtenteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/prenotazione")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneMapper prenotazioneMapper;

    @Autowired
    PrenotazioneService prenotazioneService;

    @Autowired
    UtenteService utenteService;

    @GetMapping("/estraiPrenotazioniUtente/{idUtente}")
    public List<PrenotazioneDTO> estraiPrenotazioniUtente(
            @PathVariable(value = "idUtente", required = true) Long idUtente) {

        List<PrenotazioneDTO> listaPrenotazioniDTO = new ArrayList<>();

        List<Prenotazione> listaPrenotazioni = prenotazioneService
                .caricaListaPrenotazioniByUtente(utenteService.caricaSingoloElemento(idUtente));

        for (Prenotazione item : listaPrenotazioni) {
            listaPrenotazioniDTO.add(prenotazioneMapper.toDto(item));
        }
        return listaPrenotazioniDTO;
    }

    @PostMapping("/changeApproval/{id}/{flagApprovazione}")
    public void changeApproval(@PathVariable(value = "id", required = true) Long id,
            @PathVariable(value = "flagApprovazione", required = true) Boolean flagApprovazione) {
        prenotazioneService.cambiaStatoPrenotazione(id, flagApprovazione);
    }

    @PutMapping("/modifica")
    public PrenotazioneDTO modificaPrenotazione(@RequestBody PrenotazioneDTO bodyPrenotazione) {

        return prenotazioneMapper
                .toDto(prenotazioneService.modificaPrenotazioneInTempo(prenotazioneMapper.toEntity(bodyPrenotazione)));

    }

    @DeleteMapping("/delete/{id}")
    public void cancellaPrenotazione(@PathVariable(value = "id", required = true) Long id) {
        prenotazioneService.cancellaPrenotazioneByIdInTempo(id);
    }

    @PostMapping("/inserisciNuova")
    public PrenotazioneDTO inserisciNuovaPrenotazione(@RequestBody PrenotazioneDTO bodyPrenotazione) {
        if (bodyPrenotazione.getId() != null)
            throw new RuntimeException("Non è ammesso fornire un id per la creazione");

        return prenotazioneMapper
                .toDto(prenotazioneService.inserisciNuovo(prenotazioneMapper.toEntity(bodyPrenotazione)));

    }

}
