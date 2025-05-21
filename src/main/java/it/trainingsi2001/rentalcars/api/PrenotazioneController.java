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
import it.trainingsi2001.rentalcars.dto.PrenotazioneExampleDTO;
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

    @GetMapping
    public List<PrenotazioneDTO> estraiPrenotazioni() {

        List<PrenotazioneDTO> listaPrenotazioniDTO = new ArrayList<>();

        List<Prenotazione> listaPrenotazioni = prenotazioneService
                .listAll();

        for (Prenotazione item : listaPrenotazioni) {
            listaPrenotazioniDTO.add(prenotazioneMapper.toDto(item));
        }
        return listaPrenotazioniDTO;
    }

    @GetMapping("/{id}")
    public PrenotazioneDTO getPrenotazione(@PathVariable(value = "id", required = true) Long id) {
        return prenotazioneMapper.toDto(prenotazioneService.caricaSingoloElementoConVeicoloEUtente(id));
    }

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

    @PutMapping("/changeApproval/{id}/{flagApprovazione}")
    public PrenotazioneDTO changeApproval(@PathVariable(value = "id", required = true) Long id,
            @PathVariable(value = "flagApprovazione", required = true) Boolean flagApprovazione) {
        return prenotazioneMapper.toDto(prenotazioneService.cambiaStatoPrenotazione(id, flagApprovazione));
    }

    @PutMapping
    public PrenotazioneDTO modificaPrenotazione(@RequestBody PrenotazioneDTO bodyPrenotazione) {

        return prenotazioneMapper
                .toDto(prenotazioneService.modificaPrenotazioneInTempo(prenotazioneMapper.toEntity(bodyPrenotazione)));

    }

    @DeleteMapping("/{id}")
    public void cancellaPrenotazione(@PathVariable(value = "id", required = true) Long id) {
        prenotazioneService.cancellaPrenotazioneByIdInTempo(id);
    }

    @PostMapping
    public PrenotazioneDTO inserisciNuovaPrenotazione(@RequestBody PrenotazioneDTO bodyPrenotazione) {
        if (bodyPrenotazione.getId() != null)
            throw new RuntimeException("Non è ammesso fornire un id per la creazione");

        return prenotazioneMapper
                .toDto(prenotazioneService.inserisciNuovo(prenotazioneMapper.toEntity(bodyPrenotazione)));

    }

    @PostMapping("/filterSearch")
    public List<PrenotazioneDTO> filterSearch(@RequestBody PrenotazioneExampleDTO entity) {
        List<PrenotazioneDTO> prenotazioniByFiltersDTO = new ArrayList<>();
        List<Prenotazione> prenotazioniByFilters = prenotazioneService
                .caricaPrenotazioniByFilters(prenotazioneMapper.toExampleEntity(entity));

        for (Prenotazione item : prenotazioniByFilters) {
            prenotazioniByFiltersDTO.add(prenotazioneMapper.toDto(item));
        }

        return prenotazioniByFiltersDTO;
    }

}
