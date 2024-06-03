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
@Table(name = "TB_PROJETOS_GS1_JAVA")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PROJETOS")
    @SequenceGenerator(name = "SQ_PROJETOS", sequenceName = "SQ_PROJETOS", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_PROJETOS")
    private Long id;

    @Column(name = "NOME_PROJETO")
    private String nomeProjeto;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DATA_INICIO")
    private Date dataInicio;

    @Column(name = "DATA_FIM")
    private Date dataFim;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "USUARIOS_ID_USUARIO", referencedColumnName = "ID_USUARIO", foreignKey = @ForeignKey(name = "GS1_PROJETOS_USUARIOS_FK"))
    private Usuario usuario;
}

