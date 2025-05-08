package it.trainingsi2001.rentalcars.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.trainingsi2001.rentalcars.dto.PrenotazioneDTO;
import it.trainingsi2001.rentalcars.entities.Prenotazione;
import it.trainingsi2001.rentalcars.entities.Utente;
import it.trainingsi2001.rentalcars.entities.Veicolo;
import jakarta.annotation.PostConstruct;

@Component
public class PrenotazioneMapper {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configureMappings() {
        modelMapper.typeMap(Prenotazione.class, PrenotazioneDTO.class).addMappings(mapper -> {
            mapper.skip(PrenotazioneDTO::setUtente);
        });

        modelMapper.typeMap(Prenotazione.class, PrenotazioneDTO.class).addMappings(mapper -> {
            mapper.skip(PrenotazioneDTO::setVeicolo);
        });

        modelMapper.typeMap(PrenotazioneDTO.class, Prenotazione.class).addMappings(mapper -> {
            mapper.skip(Prenotazione::setUtente);
        });

        modelMapper.typeMap(PrenotazioneDTO.class, Prenotazione.class).addMappings(mapper -> {
            mapper.skip(Prenotazione::setVeicolo);
        });
    }

    public PrenotazioneDTO toDto(Prenotazione prenotazione) {
        PrenotazioneDTO dto = modelMapper.map(prenotazione, PrenotazioneDTO.class);
        dto.setUtente(prenotazione.getUtente().getId());
        dto.setVeicolo(prenotazione.getVeicolo().getId());
        return dto;
    }

    public Prenotazione toEntity(PrenotazioneDTO dto) {
        Prenotazione prenotazione = modelMapper.map(dto, Prenotazione.class);
        Utente utente = new Utente();
        utente.setId(dto.getUtente());
        Veicolo veicolo = new Veicolo();
        veicolo.setId(dto.getVeicolo());
        prenotazione.setUtente(utente);
        prenotazione.setVeicolo(veicolo);
        return prenotazione;
    }
}
