package br.fiap.com.global_solution_java.service;

import br.fiap.com.global_solution_java.dto.request.UsuarioRequest;
import br.fiap.com.global_solution_java.dto.response.UsuarioResponse;
import br.fiap.com.global_solution_java.entity.Usuario;
import br.fiap.com.global_solution_java.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UsuarioService implements ServiceDTO<Usuario, UsuarioRequest, UsuarioResponse>{

    @Autowired
    private UsuarioRepository repo;

    @Override
    public Collection<Usuario> findAll(Example<Usuario> example) {
        return repo.findAll(example);
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario e) {
        return repo.save(e);
    }

    @Override
    public Usuario toEntity(UsuarioRequest dto) {
        return Usuario.builder()
                .nomeUsuario(dto.nomeUsuario())
                .senha(dto.senha())
                .email(dto.email())
                .cargo(dto.cargo())
                .dataCriacao(dto.dataCriacao())
                .build();
    }

    @Override
    public UsuarioResponse toResponse(Usuario e) {
        return UsuarioResponse.builder()
                .id(e.getId())
                .nomeUsuario(e.getNomeUsuario())
                .email(e.getEmail())
                .cargo(e.getCargo())
                .dataCriacao(e.getDataCriacao())
                .build();
    }
}
