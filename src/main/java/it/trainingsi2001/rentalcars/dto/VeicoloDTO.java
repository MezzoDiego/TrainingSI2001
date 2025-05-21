package it.trainingsi2001.rentalcars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VeicoloDTO {
    private Long id;

    private String casaCostruttrice;

    private String modello;

    private String annoImmatricolazione;

    private String targa;

    private Integer cilindrata;

    private String emissioni;

    private Integer potenza;

    private String numeroTelaio;

    private String kilometraggio;

    private String consumoMedioCarburanteUrbano;

    private String consumoMedioCarburanteExtraurbano;

    private String alimentazione;

    private TipologiaDTO tipologia;
}
