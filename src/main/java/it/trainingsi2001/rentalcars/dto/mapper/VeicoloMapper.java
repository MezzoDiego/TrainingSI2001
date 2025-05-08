package it.trainingsi2001.rentalcars.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.trainingsi2001.rentalcars.dto.VeicoloDTO;
import it.trainingsi2001.rentalcars.entities.Tipologia;
import it.trainingsi2001.rentalcars.entities.Veicolo;
import jakarta.annotation.PostConstruct;

@Component
public class VeicoloMapper {
    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configureMappings() {
        modelMapper.typeMap(Veicolo.class, VeicoloDTO.class).addMappings(mapper -> {
            mapper.skip(VeicoloDTO::setTipologia); 
        });

        modelMapper.typeMap(VeicoloDTO.class, Veicolo.class).addMappings(mapper -> {
            mapper.skip(Veicolo::setTipologia); 
        });
    }

    public VeicoloDTO toDto(Veicolo veicolo) {
        VeicoloDTO dto = modelMapper.map(veicolo, VeicoloDTO.class);
        dto.setTipologia(veicolo.getTipologia().getId());
        return dto;
    }

    public Veicolo toEntity(VeicoloDTO dto) {
        Veicolo veicolo = modelMapper.map(dto, Veicolo.class);
        Tipologia tipologia = new Tipologia();
        tipologia.setId(dto.getTipologia());
        veicolo.setTipologia(tipologia);
        return veicolo;
    }
}
