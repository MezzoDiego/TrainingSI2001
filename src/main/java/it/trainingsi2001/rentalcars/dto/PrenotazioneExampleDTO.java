package it.trainingsi2001.rentalcars.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PrenotazioneExampleDTO {
    private Long id;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private Boolean flagApprovazione;

    private UtenteDTO utente;
    private VeicoloDTO veicolo;
}
