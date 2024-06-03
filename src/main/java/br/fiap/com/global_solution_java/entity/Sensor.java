package br.fiap.com.global_solution_java.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_SENSORES_GS1_JAVA")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SENSORES")
    @SequenceGenerator(name = "SQ_SENSORES", sequenceName = "SQ_SENSORES", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_SENSOR")
    private Long id;

    @Column(name = "TIPO_SENSOR")
    private String tipoSensor;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "LOCALIZACAO")
    private String localizacao;

    @Column(name = "DATA_INSTALACAO")
    private Date dataInstalacao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "PROJETOS_ID_PROJETOS", referencedColumnName = "ID_PROJETOS", foreignKey = @ForeignKey(name = "GS1_SENSORES_PROJETOS_FK"))
    private Projeto projeto;
}

