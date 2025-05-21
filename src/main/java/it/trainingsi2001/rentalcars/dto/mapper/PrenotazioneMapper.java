package it.trainingsi2001.rentalcars.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.trainingsi2001.rentalcars.dto.PrenotazioneDTO;
import it.trainingsi2001.rentalcars.dto.PrenotazioneExampleDTO;
import it.trainingsi2001.rentalcars.dto.TipologiaDTO;
import it.trainingsi2001.rentalcars.dto.UtenteDTO;
import it.trainingsi2001.rentalcars.dto.VeicoloDTO;
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
    public void init() {
        modelMapper.typeMap(Utente.class, UtenteDTO.class)
                .addMappings(mapper -> mapper.skip(UtenteDTO::setRuolo));
    }

    public PrenotazioneDTO toDto(Prenotazione prenotazione) {
        PrenotazioneDTO dto = modelMapper.map(prenotazione, PrenotazioneDTO.class);
        dto.setUtente(UtenteDTO.builder()
                .id(prenotazione.getUtente().getId() != null ? prenotazione.getUtente().getId() : null)
                .nome(prenotazione.getUtente().getNome() != null ? prenotazione.getUtente().getNome() : null)
                .cognome(prenotazione.getUtente().getCognome() != null ? prenotazione.getUtente().getCognome() : null)
                .dataDiNascita(prenotazione.getUtente().getDataDiNascita() != null ? prenotazione.getUtente().getDataDiNascita() : null)
                .username(prenotazione.getUtente().getUsername() != null ? prenotazione.getUtente().getUsername() : null)
                .stato(prenotazione.getUtente().getStato() != null ? StatoUtente.valueOf(prenotazione.getUtente().getStato().toString()).toString() : null)
                .ruolo(prenotazione.getUtente().getRuolo() != null ? prenotazione.getUtente().getRuolo().getId() : null)
                .build());
        dto.setVeicolo(VeicoloDTO.builder()
                .id(prenotazione.getVeicolo().getId() != null ? prenotazione.getVeicolo().getId() : null)
                .alimentazione(prenotazione.getVeicolo().getAlimentazione() != null ? prenotazione.getVeicolo().getAlimentazione() : null)
                .annoImmatricolazione(prenotazione.getVeicolo().getAnnoImmatricolazione() != null ? prenotazione.getVeicolo().getAnnoImmatricolazione() : null)
                .casaCostruttrice(prenotazione.getVeicolo().getCasaCostruttrice() != null ? prenotazione.getVeicolo().getCasaCostruttrice() : null)
                .cilindrata(prenotazione.getVeicolo().getCilindrata() != null ? prenotazione.getVeicolo().getCilindrata() : null)
                .consumoMedioCarburanteExtraurbano(prenotazione.getVeicolo().getConsumoMedioCarburanteExtraurbano() != null ? prenotazione.getVeicolo().getConsumoMedioCarburanteExtraurbano() : null)
                .consumoMedioCarburanteUrbano(prenotazione.getVeicolo().getConsumoMedioCarburanteUrbano() != null ? prenotazione.getVeicolo().getConsumoMedioCarburanteUrbano() : null)
                .emissioni(prenotazione.getVeicolo().getEmissioni() != null ? prenotazione.getVeicolo().getEmissioni() : null)
                .kilometraggio(prenotazione.getVeicolo().getKilometraggio() != null ? prenotazione.getVeicolo().getKilometraggio() : null)
                .modello(prenotazione.getVeicolo().getModello() != null ? prenotazione.getVeicolo().getModello() : null)
                .numeroTelaio(prenotazione.getVeicolo().getNumeroTelaio() != null ? prenotazione.getVeicolo().getNumeroTelaio() : null)
                .potenza(prenotazione.getVeicolo().getPotenza() != null ? prenotazione.getVeicolo().getPotenza() : null)
                .targa(prenotazione.getVeicolo().getTarga() != null ? prenotazione.getVeicolo().getTarga() : null)
                .tipologia(TipologiaDTO.builder()
                        .id(prenotazione.getVeicolo().getTipologia() != null ? prenotazione.getVeicolo().getTipologia().getId() : null)
                        .descrizione(prenotazione.getVeicolo().getTipologia() != null ? prenotazione.getVeicolo().getTipologia().getDescrizione() : null).build())
                .build());
        return dto;
    }

    public Prenotazione toEntity(PrenotazioneDTO dto) {
        Prenotazione prenotazione = modelMapper.map(dto, Prenotazione.class);

        if (dto.getUtente() != null) {
            prenotazione.setUtente(Utente.builder()
                    .id(dto.getUtente().getId() != null ? dto.getUtente().getId() : null)
                    .nome(dto.getUtente().getNome() != null ? dto.getUtente().getNome() : null)
                    .cognome(dto.getUtente().getCognome() != null ? dto.getUtente().getCognome() : null)
                    .dataDiNascita(
                            dto.getUtente().getDataDiNascita() != null ? dto.getUtente().getDataDiNascita() : null)
                    .username(dto.getUtente().getUsername() != null ? dto.getUtente().getUsername() : null)
                    .stato(dto.getUtente().getStato() != null ? StatoUtente.valueOf(dto.getUtente().getStato()) : null)
                    .build());
        }

        if (dto.getVeicolo() != null) {
            prenotazione.setVeicolo(Veicolo.builder()
                    .id(dto.getVeicolo().getId())
                    .alimentazione(dto.getVeicolo().getAlimentazione())
                    .annoImmatricolazione(dto.getVeicolo().getAnnoImmatricolazione())
                    .casaCostruttrice(dto.getVeicolo().getCasaCostruttrice())
                    .cilindrata(dto.getVeicolo().getCilindrata())
                    .consumoMedioCarburanteExtraurbano(dto.getVeicolo().getConsumoMedioCarburanteExtraurbano())
                    .consumoMedioCarburanteUrbano(dto.getVeicolo().getConsumoMedioCarburanteUrbano())
                    .emissioni(dto.getVeicolo().getEmissioni())
                    .kilometraggio(dto.getVeicolo().getKilometraggio())
                    .modello(dto.getVeicolo().getModello())
                    .numeroTelaio(dto.getVeicolo().getNumeroTelaio())
                    .potenza(dto.getVeicolo().getPotenza())
                    .targa(dto.getVeicolo().getTarga())
                    .tipologia(Tipologia.builder()
                            .id(dto.getVeicolo().getTipologia().getId())
                            .descrizione(dto.getVeicolo().getTipologia().getDescrizione()).build())
                    .build());
        }
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
                            ? Tipologia.builder().id(dto.getVeicolo().getTipologia().getId())
                                    .descrizione(dto.getVeicolo().getTipologia().getDescrizione()).build()
                            : null)
                    .build());
        }

        return prenotazione;
    }

}
