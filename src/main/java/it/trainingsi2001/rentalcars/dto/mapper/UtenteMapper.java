package it.trainingsi2001.rentalcars.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.trainingsi2001.rentalcars.dto.UtenteDTO;
import it.trainingsi2001.rentalcars.entities.Ruolo;
import it.trainingsi2001.rentalcars.entities.Utente;
import jakarta.annotation.PostConstruct;

@Component
public class UtenteMapper {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configureMappings() {
        modelMapper.typeMap(Utente.class, UtenteDTO.class).addMappings(mapper -> {
            mapper.skip(UtenteDTO::setRuolo); 
        });

        modelMapper.typeMap(UtenteDTO.class, Utente.class).addMappings(mapper -> {
            mapper.skip(Utente::setRuolo); 
        });
    }

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
