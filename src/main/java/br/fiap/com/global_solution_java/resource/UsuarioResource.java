package br.fiap.com.global_solution_java.resource;

import br.fiap.com.global_solution_java.dto.request.UsuarioRequest;
import br.fiap.com.global_solution_java.dto.response.UsuarioResponse;
import br.fiap.com.global_solution_java.entity.Usuario;
import br.fiap.com.global_solution_java.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioResource implements ResourceDTO<UsuarioRequest, UsuarioResponse> {

    @Autowired
    private UsuarioService service;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Valid UsuarioRequest r) {
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
    public ResponseEntity<Collection<UsuarioResponse>> findAll(
            @RequestParam(name = "usuario.id", required = false) Long id,
            @RequestParam(name = "usuario.nomeUsuario", required = false) String nomeUsuario,
            @RequestParam(name = "usuario.email", required = false) String email,
            @RequestParam(name = "usuario.cargo", required = false) String cargo,
            @RequestParam(name = "usuario.dataCriacao", required = false) Date dataCriacao
    ) {
        var usuario = Usuario.builder()
                .id(id)
                .nomeUsuario(nomeUsuario)
                .email(email)
                .cargo(cargo)
                .dataCriacao(dataCriacao)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Usuario> example = Example.of(usuario, matcher);
        Collection<Usuario> usuarios = service.findAll(example);

        var response = usuarios.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }
}

