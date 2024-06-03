package br.fiap.com.global_solution_java.resource;

import br.fiap.com.global_solution_java.dto.request.HabitatRequest;
import br.fiap.com.global_solution_java.dto.response.HabitatResponse;
import br.fiap.com.global_solution_java.entity.Habitat;
import br.fiap.com.global_solution_java.entity.Projeto;
import br.fiap.com.global_solution_java.service.HabitatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/habitats")
public class HabitatResource implements ResourceDTO<HabitatRequest, HabitatResponse> {

    @Autowired
    private HabitatService service;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<HabitatResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<HabitatResponse> save(@RequestBody @Valid HabitatRequest r) {
        var entity = service.toEntity(r);
        var saved = service.save(entity);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        var response = service.toResponse(saved);
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Collection<HabitatResponse>> findAll(
            @RequestParam(name = "habitat.id", required = false) Long id,
            @RequestParam(name = "habitat.nomeHabitat", required = false) String nomeHabitat,
            @RequestParam(name = "habitat.descricao", required = false) String descricao,
            @RequestParam(name = "habitat.localizacao", required = false) String localizacao,
            @RequestParam(name = "habitat.status", required = false) String status,
            @RequestParam(name = "habitat.projeto", required = false) Projeto projeto
    ) {
        var habitat = Habitat.builder()
                .id(id)
                .nomeHabitat(nomeHabitat)
                .descricao(descricao)
                .localizacao(localizacao)
                .status(status)
                .projeto(projeto)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Habitat> example = Example.of(habitat, matcher);
        Collection<Habitat> habitats = service.findAll(example);

        var response = habitats.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }
}
