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
@Table(name = "TB_USUARIOS_GS1_GS1_JAVA")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIOS_GS1")
    @SequenceGenerator(name = "SQ_USUARIOS_GS1", sequenceName = "SQ_USUARIOS_GS1", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOME_USUARIO")
    private String nomeUsuario;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CARGO")
    private String cargo;

    @Column(name = "DATA_CRIACAO")
    private Date dataCriacao;
}
