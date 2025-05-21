package it.trainingsi2001.rentalcars.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trainingsi2001.rentalcars.dto.UtenteDTO;
import it.trainingsi2001.rentalcars.dto.mapper.UtenteMapper;
import it.trainingsi2001.rentalcars.entities.Ruolo;
import it.trainingsi2001.rentalcars.entities.Utente;
import it.trainingsi2001.rentalcars.security.dto.UtenteInfoJWTResponseDTO;
import it.trainingsi2001.rentalcars.service.UtenteService;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

    @Autowired
    UtenteMapper utenteMapper;

    @Autowired
    UtenteService utenteService;

    @PostMapping
    public UtenteDTO creaUtente(@RequestBody UtenteDTO bodyUtente) {

        if (bodyUtente.getId() != null)
            throw new RuntimeException("Non è ammesso fornire un id per la creazione");

        return utenteMapper.toDto(utenteService.inserisciNuovo(utenteMapper.toEntity(bodyUtente)));

    }

    @GetMapping("/getCustomers")
    public List<UtenteDTO> getCustomers() {
        List<UtenteDTO> customersDTO = new ArrayList<>();
        List<Utente> customers = utenteService.caricaListaUtentiByRuolo(Ruolo.builder().id(2L).build());

        for (Utente item : customers) {
            customersDTO.add(utenteMapper.toDto(item));
        }
        return customersDTO;
    }

    @GetMapping("/{id}")
    public UtenteDTO getUtente(@PathVariable(value = "id", required = true) Long id) {
        return utenteMapper.toDto(utenteService.caricaSingoloElemento(id));
    }

    @PutMapping
    public UtenteDTO updateUtente(@RequestBody UtenteDTO bodyUtente) {

        return utenteMapper.toDto(utenteService.aggiorna(utenteMapper.toEntity(bodyUtente)));

    }

    @DeleteMapping("/{id}")
    public void deleteUtente(@PathVariable(value = "id", required = true) Long id) {
        utenteService.rimuovi(id);
    }

    @PostMapping("/filterSearch")
    public List<UtenteDTO> filterSearch(@RequestBody UtenteDTO entity) {
        List<UtenteDTO> utentiByFiltersDTO = new ArrayList<>();
        List<Utente> utentiByFilters = utenteService
                .caricaUtentiByFilters(utenteMapper.toEntity(entity));

        for (Utente item : utentiByFilters) {
            utentiByFiltersDTO.add(utenteMapper.toDto(item));
        }

        return utentiByFiltersDTO;
    }

    @GetMapping(value = "/userInfo")
    public ResponseEntity<UtenteInfoJWTResponseDTO> getUserInfo() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Utente utenteLoggato = utenteService.findByUsername(username);
        String ruolo = utenteLoggato.getRuolo().getDescrizione();

        return ResponseEntity.ok(UtenteInfoJWTResponseDTO.builder()
                .id(utenteLoggato.getId().toString())
                .nome(utenteLoggato.getNome())
                .cognome(utenteLoggato.getCognome())
                .username(utenteLoggato.getUsername())
                .role(ruolo)
                .build());
    }
}
