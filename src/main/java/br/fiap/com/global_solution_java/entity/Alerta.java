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
@Table(name = "TB_ALERTAS_GS1_JAVA")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ALERTAS")
    @SequenceGenerator(name = "SQ_ALERTAS", sequenceName = "SQ_ALERTAS", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_ALERTA")
    private Long id;

    @Column(name = "TIPO_ALERTA")
    private String tipoAlerta;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DATA_GERACAO")
    private Date dataGeracao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "HABITATS_ID_HABITAT", referencedColumnName = "ID_HABITAT", foreignKey = @ForeignKey(name = "GS1_ALERTAS_HABITATS_FK"))
    private Habitat habitat;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "USUARIOS_ID_USUARIO", referencedColumnName = "ID_USUARIO", foreignKey = @ForeignKey(name = "GS1_ALERTAS_USUARIOS_FK"))
    private Usuario usuario;
}
