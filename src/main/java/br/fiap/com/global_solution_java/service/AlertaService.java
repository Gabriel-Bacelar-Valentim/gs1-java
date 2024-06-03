package br.fiap.com.global_solution_java.service;

import br.fiap.com.global_solution_java.dto.request.AlertaRequest;
import br.fiap.com.global_solution_java.dto.response.AlertaResponse;
import br.fiap.com.global_solution_java.entity.Alerta;
import br.fiap.com.global_solution_java.entity.Habitat;
import br.fiap.com.global_solution_java.entity.Usuario;
import br.fiap.com.global_solution_java.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class AlertaService implements ServiceDTO<Alerta, AlertaRequest, AlertaResponse>{

    @Autowired
    private AlertaRepository repo;

    @Autowired
    private HabitatService habitatService;

    @Autowired
    private UsuarioService usuarioService;


    @Override
    public Collection<Alerta> findAll(Example<Alerta> example) {
        return repo.findAll( example );
    }

    @Override
    public Alerta findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Alerta save(Alerta e) {
        return repo.save( e );
    }

    @Override
    public Alerta toEntity(AlertaRequest dto) {
        if (Objects.isNull( dto )) return null;

        var habitat = this.findById(dto.habitat().id());
        var usuario = this.findById(dto.usuario().id());

        return Alerta.builder()
                .tipoAlerta(dto.tipoAlerta())
                .descricao(dto.descricao())
                .dataGeracao(dto.dataGeracao())
                .habitat(habitat.getHabitat())
                .usuario(usuario.getUsuario())
                .build();
    }

    @Override
    public AlertaResponse toResponse(Alerta e) {
        return AlertaResponse.builder()
                .id(e.getId())
                .tipoAlerta(e.getTipoAlerta())
                .descricao(e.getDescricao())
                .habitat( habitatService.toResponse(Habitat.builder().build()))
                .usuario( usuarioService.toResponse(Usuario.builder().build()))
                .build();
    }
}
