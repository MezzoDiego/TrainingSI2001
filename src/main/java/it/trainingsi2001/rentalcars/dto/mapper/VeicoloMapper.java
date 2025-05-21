package it.trainingsi2001.rentalcars.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.trainingsi2001.rentalcars.dto.TipologiaDTO;
import it.trainingsi2001.rentalcars.dto.VeicoloDTO;
import it.trainingsi2001.rentalcars.entities.Tipologia;
import it.trainingsi2001.rentalcars.entities.Veicolo;

@Component
public class VeicoloMapper {
    @Autowired
    private ModelMapper modelMapper;

    public VeicoloDTO toDto(Veicolo veicolo) {
        VeicoloDTO dto = modelMapper.map(veicolo, VeicoloDTO.class);

        dto.setTipologia(TipologiaDTO.builder()
                .id(veicolo.getTipologia().getId())
                .descrizione(veicolo.getTipologia().getDescrizione())
                .build());

        return dto;
    }

    public Veicolo toEntity(VeicoloDTO dto) {
        Veicolo veicolo = modelMapper.map(dto, Veicolo.class);

        if (dto.getTipologia() != null) {
            veicolo.setTipologia(Tipologia.builder()
                    .id(dto.getTipologia().getId()).descrizione(dto.getTipologia().getDescrizione())
                    .build());
        }

        return veicolo;
    }

}
