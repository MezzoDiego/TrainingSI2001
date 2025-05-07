package it.trainingsi2001.rentalcars.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "veicolo")
public class Veicolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "casaCostruttrice")
    private String casaCostruttrice;

    @Column(name = "modello")
    private String modello;
    @Column(name = "annoImmatricolazione")
    private String annoImmatricolazione;

    @Column(name = "targa")
    private String targa;

    @Column(name = "cilindrata")
    private Integer cilindrata;

    @Column(name = "emissioni")
    private String emissioni;

    @Column(name = "potenza")
    private Integer potenza;

    @Column(name = "numeroTelaio")
    private String numeroTelaio;

    @Column(name = "kilometraggio")
    private String kilometraggio;

    @Column(name = "consumoMedioCarburanteUrbano")
    private String consumoMedioCarburanteUrbano;

    @Column(name = "consumoMedioCarburanteExtraurbano")
    private String consumoMedioCarburanteExtraurbano;

    @Column(name = "alimentazione")
    private String alimentazione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipologia", nullable = false)
    private Tipologia tipologia;
}
