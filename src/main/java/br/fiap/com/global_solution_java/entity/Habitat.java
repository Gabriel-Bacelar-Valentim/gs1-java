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
@Table(name = "TB_HABITATS_GS1_JAVA")
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HABITATS")
    @SequenceGenerator(name = "SQ_HABITATS", sequenceName = "SQ_HABITATS", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_HABITAT")
    private Long id;

    @Column(name = "NOME_HABITAT")
    private String nomeHabitat;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "LOCALIZACAO")
    private String localizacao;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "PROJETOS_ID_PROJETOS", referencedColumnName = "ID_PROJETOS", foreignKey = @ForeignKey(name = "GS1_HABITATS_PROJETOS_FK"))
    private Projeto projeto;
}

