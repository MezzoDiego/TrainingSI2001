package it.trainingsi2001.rentalcars.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trainingsi2001.rentalcars.dto.VeicoloDTO;
import it.trainingsi2001.rentalcars.dto.mapper.VeicoloMapper;
import it.trainingsi2001.rentalcars.entities.Veicolo;
import it.trainingsi2001.rentalcars.service.VeicoloService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/veicolo")
public class VeicoloController {

    @Autowired
    VeicoloService veicoloService;

    @Autowired
    VeicoloMapper veicoloMapper;

    @GetMapping("/listAll")
    public List<VeicoloDTO> listAllVeicoli() {
        List<VeicoloDTO> veicoliDTO = new ArrayList<>();
        List<Veicolo> veicoliLoadedFromDB = veicoloService.listAll();

        for (Veicolo item : veicoliLoadedFromDB) {
            veicoliDTO.add(veicoloMapper.toDto(item));
        }

        return veicoliDTO;
    }

    @PostMapping("/inserisciNuovo")
    public VeicoloDTO inserisciNuovoVeicolo(@RequestBody VeicoloDTO bodyVeicolo) {

        return veicoloMapper.toDto(veicoloService.inserisciNuovo(veicoloMapper.toEntity(bodyVeicolo)));

    }

    @DeleteMapping("/delete/{id}")
    public void inserisciNuovoVeicolo(@PathVariable(value = "id", required = true) Long id) {
        veicoloService.rimuovi(id);
    }

    @PutMapping("/modifica")
    public VeicoloDTO modificaVeicolo(@RequestBody VeicoloDTO bodyVeicolo) {

        return veicoloMapper.toDto(veicoloService.aggiorna(veicoloMapper.toEntity(bodyVeicolo)));

    }

}
