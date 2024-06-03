package br.fiap.com.global_solution_java.service;


import br.fiap.com.global_solution_java.dto.request.ProjetoRequest;
import br.fiap.com.global_solution_java.dto.response.ProjetoResponse;
import br.fiap.com.global_solution_java.entity.Projeto;
import br.fiap.com.global_solution_java.entity.Usuario;
import br.fiap.com.global_solution_java.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class ProjetoService implements ServiceDTO<Projeto, ProjetoRequest, ProjetoResponse>{

    @Autowired
    private ProjetoRepository repo;

    @Autowired
    private UsuarioService usuarioService;


    @Override
    public Collection<Projeto> findAll(Example<Projeto> example) {
        return repo.findAll(example);
    }

    @Override
    public Projeto findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Projeto save(Projeto e) {
        return repo.save(e);
    }

    @Override
    public Projeto toEntity(ProjetoRequest dto) {
        if (Objects.isNull( dto )) return null;

        var usuario = this.findById(dto.usuario().id());

        return Projeto.builder()
                .nomeProjeto(dto.nomeProjeto())
                .descricao(dto.descricao())
                .dataInicio(dto.dataInicio())
                .dataFim(dto.dataFim())
                .usuario( usuario.getUsuario())
                .build();
    }

    @Override
    public ProjetoResponse toResponse(Projeto e) {
        return ProjetoResponse.builder()
                .id(e.getId())
                .nomeProjeto(e.getNomeProjeto())
                .descricao(e.getDescricao())
                .dataInicio(e.getDataInicio())
                .dataFim(e.getDataFim())
                .usuario( usuarioService.toResponse(Usuario.builder().build()))
                .build();
    }
}
