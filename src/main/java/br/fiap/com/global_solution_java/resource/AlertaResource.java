package br.fiap.com.global_solution_java.resource;

import br.fiap.com.global_solution_java.dto.request.AlertaRequest;
import br.fiap.com.global_solution_java.dto.response.AlertaResponse;
import br.fiap.com.global_solution_java.entity.Alerta;
import br.fiap.com.global_solution_java.entity.Habitat;
import br.fiap.com.global_solution_java.entity.Usuario;
import br.fiap.com.global_solution_java.service.AlertaService;
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
@RequestMapping("/alertas")
public class AlertaResource implements ResourceDTO<AlertaRequest, AlertaResponse>{

    @Autowired
    private AlertaService service;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<AlertaResponse> findById(@PathVariable Long id) {
        var entity = service.findById( id );
        if (Objects.isNull( id )) return ResponseEntity.notFound().build();
        var response = service.toResponse( entity );
        return ResponseEntity.ok( response );
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<AlertaResponse> save(@RequestBody @Valid AlertaRequest r) {
        var entity = service.toEntity( r );
        var saved = service.save( entity );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        var response = service.toResponse( saved );
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Collection<AlertaResponse>> findAll(
            @RequestParam(name = "alerta.id", required = false) Long id,
            @RequestParam(name = "alerta.tipoAlerta", required = false) String tipoAlerta,
            @RequestParam(name = "alerta.descricao", required = false) String descricao,
            @RequestParam(name = "alerta.dataGeracao", required = false) Date dataGeracao,
            @RequestParam(name = "habitat.habitat", required = false)Habitat habitat,
            @RequestParam(name = "usuario.usuario", required = false)Usuario usuario

            ){
        var alerta = Alerta.builder()
                .id(id)
                .tipoAlerta(tipoAlerta)
                .descricao(descricao)
                .dataGeracao(dataGeracao)
                .habitat(habitat)
                .usuario(usuario)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Alerta> example = Example.of(alerta, matcher);
        Collection<Alerta> alertas = service.findAll(example);

        var response = alertas.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);

    }
}
