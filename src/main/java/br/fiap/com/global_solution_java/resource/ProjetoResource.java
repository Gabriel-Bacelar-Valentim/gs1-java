package br.fiap.com.global_solution_java.resource;

import br.fiap.com.global_solution_java.dto.request.ProjetoRequest;
import br.fiap.com.global_solution_java.dto.response.ProjetoResponse;
import br.fiap.com.global_solution_java.entity.Projeto;
import br.fiap.com.global_solution_java.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/projetos")
public class ProjetoResource implements ResourceDTO<ProjetoRequest, ProjetoResponse> {

    @Autowired
    private ProjetoService service;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjetoResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<ProjetoResponse> save(@RequestBody @Valid ProjetoRequest r) {
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
    public ResponseEntity<Collection<ProjetoResponse>> findAll(
            @RequestParam(name = "projeto.id", required = false) Long id,
            @RequestParam(name = "projeto.nomeProjeto", required = false) String nomeProjeto,
            @RequestParam(name = "projeto.descricao", required = false) String descricao,
            @RequestParam(name = "projeto.dataInicio", required = false) Date dataInicio,
            @RequestParam(name = "projeto.dataFim", required = false) Date dataFim
    ) {
        var projeto = Projeto.builder()
                .id(id)
                .nomeProjeto(nomeProjeto)
                .descricao(descricao)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Projeto> example = Example.of(projeto, matcher);
        Collection<Projeto> projetos = service.findAll(example);

        var response = projetos.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }
}

