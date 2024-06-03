package br.fiap.com.global_solution_java.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_DADOSSENSORES_GS1_JAVA")
public class DadoSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DADOSSENSORES")
    @SequenceGenerator(name = "SQ_DADOSSENSORES", sequenceName = "SQ_DADOSSENSORES", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_DADO")
    private Long id;

    @Column(name = "TEMPERATURA")
    private Double temperatura;

    @Column(name = "SALINIDADE")
    private Double salinidade;

    @Column(name = "NIVEL_PH")
    private Double nivelPh;

    @Column(name = "LOCALIZACAO")
    private String localizacao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "SENSORES_ID_SENSOR", referencedColumnName = "ID_SENSOR", foreignKey = @ForeignKey(name = "GS1_DADOS_SENSORES_SENSORES_FK"))
    private Sensor sensor;
}

