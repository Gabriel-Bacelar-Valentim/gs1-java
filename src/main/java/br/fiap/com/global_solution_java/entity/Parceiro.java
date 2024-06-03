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
@Table(name = "TB_PARCEIROS_GS1_JAVA")
public class Parceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PARCEIROS")
    @SequenceGenerator(name = "SQ_PARCEIROS", sequenceName = "SQ_PARCEIROS", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_PARCEIRO")
    private Long id;

    @Column(name = "NOME_PARCEIRO")
    private String nomeParceiro;

    @Column(name = "TIPO_PARCEIRO")
    private String tipoParceiro;

    @Column(name = "CONTATO")
    private String contato;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONE")
    private String telefone;
}

