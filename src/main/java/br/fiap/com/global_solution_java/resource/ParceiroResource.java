package br.fiap.com.global_solution_java.resource;

import br.fiap.com.global_solution_java.dto.request.ParceiroRequest;
import br.fiap.com.global_solution_java.dto.response.ParceiroResponse;
import br.fiap.com.global_solution_java.entity.Parceiro;
import br.fiap.com.global_solution_java.service.ParceiroService;
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
@RequestMapping("/parceiros")
public class ParceiroResource implements ResourceDTO<ParceiroRequest, ParceiroResponse> {

    @Autowired
    private ParceiroService service;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<ParceiroResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<ParceiroResponse> save(@RequestBody @Valid ParceiroRequest r) {
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
    public ResponseEntity<Collection<ParceiroResponse>> findAll(
            @RequestParam(name = "parceiro.id", required = false) Long id,
            @RequestParam(name = "parceiro.nomeParceiro", required = false) String nomeParceiro,
            @RequestParam(name = "parceiro.tipoParceiro", required = false) String tipoParceiro,
            @RequestParam(name = "parceiro.contato", required = false) String contato,
            @RequestParam(name = "parceiro.email", required = false) String email,
            @RequestParam(name = "parceiro.telefone", required = false) String telefone
    ) {
        var parceiro = Parceiro.builder()
                .id(id)
                .nomeParceiro(nomeParceiro)
                .tipoParceiro(tipoParceiro)
                .contato(contato)
                .email(email)
                .telefone(telefone)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Parceiro> example = Example.of(parceiro, matcher);
        Collection<Parceiro> parceiros = service.findAll(example);

        var response = parceiros.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }
}

