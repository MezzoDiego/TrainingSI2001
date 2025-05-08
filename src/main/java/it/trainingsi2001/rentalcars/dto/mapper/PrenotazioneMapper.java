package it.trainingsi2001.rentalcars.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.trainingsi2001.rentalcars.dto.PrenotazioneDTO;
import it.trainingsi2001.rentalcars.dto.PrenotazioneExampleDTO;
import it.trainingsi2001.rentalcars.entities.Prenotazione;
import it.trainingsi2001.rentalcars.entities.StatoUtente;
import it.trainingsi2001.rentalcars.entities.Tipologia;
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

    public Prenotazione toExampleEntity(PrenotazioneExampleDTO dto) {
        Prenotazione prenotazione = new Prenotazione();

        prenotazione.setDataInizio(dto.getDataInizio());
        prenotazione.setDataFine(dto.getDataFine());

        if (dto.getUtente() != null) {
            prenotazione.setUtente(Utente.builder()
                    .nome(dto.getUtente().getNome())
                    .cognome(dto.getUtente().getCognome())
                    .dataDiNascita(dto.getUtente().getDataDiNascita())
                    .username(dto.getUtente().getUsername())
                    .stato(StatoUtente.valueOf(dto.getUtente().getStato()))
                    .build());
        }

        if (dto.getVeicolo() != null) {
            prenotazione.setVeicolo(Veicolo.builder()
                    .alimentazione(
                            dto.getVeicolo().getAlimentazione() != null ? dto.getVeicolo().getAlimentazione() : null)
                    .annoImmatricolazione(dto.getVeicolo().getAnnoImmatricolazione() != null
                            ? dto.getVeicolo().getAnnoImmatricolazione()
                            : null)
                    .casaCostruttrice(
                            dto.getVeicolo().getCasaCostruttrice() != null ? dto.getVeicolo().getCasaCostruttrice()
                                    : null)
                    .cilindrata(dto.getVeicolo().getCilindrata() != null ? dto.getVeicolo().getCilindrata() : null)
                    .consumoMedioCarburanteExtraurbano(dto.getVeicolo().getConsumoMedioCarburanteExtraurbano() != null
                            ? dto.getVeicolo().getConsumoMedioCarburanteExtraurbano()
                            : null)
                    .consumoMedioCarburanteUrbano(dto.getVeicolo().getConsumoMedioCarburanteUrbano() != null
                            ? dto.getVeicolo().getConsumoMedioCarburanteUrbano()
                            : null)
                    .emissioni(dto.getVeicolo().getEmissioni() != null ? dto.getVeicolo().getEmissioni() : null)
                    .kilometraggio(
                            dto.getVeicolo().getKilometraggio() != null ? dto.getVeicolo().getKilometraggio() : null)
                    .modello(dto.getVeicolo().getModello() != null ? dto.getVeicolo().getModello() : null)
                    .numeroTelaio(
                            dto.getVeicolo().getNumeroTelaio() != null ? dto.getVeicolo().getNumeroTelaio() : null)
                    .potenza(dto.getVeicolo().getPotenza() != null ? dto.getVeicolo().getPotenza() : null)
                    .targa(dto.getVeicolo().getTarga() != null ? dto.getVeicolo().getTarga() : null)
                    .tipologia(dto.getVeicolo().getTipologia() != null
                            ? Tipologia.builder().id(dto.getVeicolo().getTipologia()).build()
                            : null)
                    .build());
        }

        return prenotazione;
    }

}
