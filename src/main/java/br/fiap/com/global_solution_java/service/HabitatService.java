package br.fiap.com.global_solution_java.service;

import br.fiap.com.global_solution_java.dto.request.HabitatRequest;
import br.fiap.com.global_solution_java.dto.response.HabitatResponse;
import br.fiap.com.global_solution_java.entity.Habitat;
import br.fiap.com.global_solution_java.entity.Projeto;
import br.fiap.com.global_solution_java.repository.HabitatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class HabitatService implements ServiceDTO<Habitat, HabitatRequest, HabitatResponse>{

    @Autowired
    private HabitatRepository repo;

    @Autowired
    private ProjetoService projetoService;

    @Override
    public Collection<Habitat> findAll(Example<Habitat> example) {
        return repo.findAll( example );
    }

    @Override
    public Habitat findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Habitat save(Habitat e) {
        return repo.save(e);
    }

    @Override
    public Habitat toEntity(HabitatRequest dto) {
        if (Objects.isNull( dto )) return null;

        var projeto = this.findById(dto.projeto().id());

        return Habitat.builder()
                .nomeHabitat(dto.nomeHabitat())
                .descricao(dto.descricao())
                .localizacao(dto.localizacao())
                .status(dto.status())
                .projeto( projeto.getProjeto())
                .build();
    }

    @Override
    public HabitatResponse toResponse(Habitat e) {
        return HabitatResponse.builder()
                .id(e.getId())
                .nomeHabitat(e.getNomeHabitat())
                .descricao(e.getDescricao())
                .localizacao(e.getLocalizacao())
                .status(e.getStatus())
                .projeto( projetoService.toResponse(Projeto.builder().build()))
                .build();
    }
}
