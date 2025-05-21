package it.trainingsi2001.rentalcars.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.trainingsi2001.rentalcars.dto.UtenteDTO;
import it.trainingsi2001.rentalcars.entities.Ruolo;
import it.trainingsi2001.rentalcars.entities.Utente;

@Component
public class UtenteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UtenteDTO toDto(Utente utente) {
        UtenteDTO dto = modelMapper.map(utente, UtenteDTO.class);
        dto.setRuolo(utente.getRuolo().getId());
        return dto;
    }

    public Utente toEntity(UtenteDTO dto) {
        Utente utente = modelMapper.map(dto, Utente.class);
        Ruolo ruolo = new Ruolo();
        ruolo.setId(dto.getRuolo());
        utente.setRuolo(ruolo);
        return utente;
    }

}
