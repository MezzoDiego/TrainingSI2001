package it.trainingsi2001.rentalcars.dto;

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
    private String nomeUtente;
    private String cognomeUtente;
    private String username;
    private String statoUtente;
    private String dataDiNascitaUtente;

    private String modelloVeicolo;
    private String targaVeicolo;
    private String telaioVeicolo;
    private String alimentazioneVeicolo;
    private String casaCostruttriceVeicolo;
    private String annoImmatricolazioneVeicolo;
    private String cilindrataVeicolo;
    private String potenzaVeicolo;
    private String consUrbVeicolo;
    private String consExtraUrbVeicolo;
    private String emissioniVeicolo;
    private String kilometraggioVeicolo;
    private String tipologiaVeicolo;

    private String dataInizioPrenotazione;
    private String dataFinePrenotazione;
}
