package it.trainingsi2001.rentalcars.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trainingsi2001.rentalcars.dto.TipologiaDTO;
import it.trainingsi2001.rentalcars.dto.VeicoloDTO;
import it.trainingsi2001.rentalcars.dto.mapper.TipologiaMapper;
import it.trainingsi2001.rentalcars.entities.Tipologia;
import it.trainingsi2001.rentalcars.service.TipologiaService;

@RestController
@RequestMapping("/api/tipologia")
public class TipologiaController {

    @Autowired
    TipologiaService tipologiaService;

    @Autowired
    TipologiaMapper tipologiaMapper;

    @GetMapping
    public List<TipologiaDTO> listAllTipologie() {
        List<TipologiaDTO> tipologieDTO = new ArrayList<>();
        List<Tipologia> tipologieLoadedFromDB = tipologiaService.listAll();

        for (Tipologia item : tipologieLoadedFromDB) {
            tipologieDTO.add(tipologiaMapper.toDto(item));
        }

        return tipologieDTO;
    }

    @GetMapping("/{id}")
    public TipologiaDTO getTipologiaById(@PathVariable(value = "id", required = true) Long id) {
        return tipologiaMapper.toDto(tipologiaService.caricaSingoloElemento(id));
    }

    @PostMapping
    public TipologiaDTO inserisciNuovaTipologia(@RequestBody TipologiaDTO bodyTipologia) {

        return tipologiaMapper.toDto(tipologiaService.inserisciNuovo(tipologiaMapper.toEntity(bodyTipologia)));

    }

    @DeleteMapping("/{id}")
    public void deleteTipologia(@PathVariable(value = "id", required = true) Long id) {
        tipologiaService.rimuovi(id);
    }

    @PutMapping
    public TipologiaDTO modificaTipologia(@RequestBody TipologiaDTO bodyTipologia) {

        return tipologiaMapper.toDto(tipologiaService.aggiorna(tipologiaMapper.toEntity(bodyTipologia)));

    }

}
