package it.trainingsi2001.rentalcars.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.trainingsi2001.rentalcars.dto.TipologiaDTO;
import it.trainingsi2001.rentalcars.entities.Tipologia;

@Component
public class TipologiaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TipologiaDTO toDto(Tipologia tipologia) {
        TipologiaDTO dto = modelMapper.map(tipologia, TipologiaDTO.class);
        return dto;
    }

    public Tipologia toEntity(TipologiaDTO dto) {
        Tipologia tipologia = modelMapper.map(dto, Tipologia.class);
        return tipologia;
    }

}
