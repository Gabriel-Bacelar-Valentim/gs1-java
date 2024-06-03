package br.fiap.com.global_solution_java.resource;

import br.fiap.com.global_solution_java.dto.request.SensorRequest;
import br.fiap.com.global_solution_java.dto.response.SensorResponse;
import br.fiap.com.global_solution_java.entity.Projeto;
import br.fiap.com.global_solution_java.entity.Sensor;
import br.fiap.com.global_solution_java.service.SensorService;
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
@RequestMapping("/sensores")
public class SensorResource implements ResourceDTO<SensorRequest, SensorResponse> {

    @Autowired
    private SensorService service;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<SensorResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<SensorResponse> save(@RequestBody @Valid SensorRequest r) {
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
    public ResponseEntity<Collection<SensorResponse>> findAll(
            @RequestParam(name = "sensor.id", required = false) Long id,
            @RequestParam(name = "sensor.tipoSensor", required = false) String tipoSensor,
            @RequestParam(name = "sensor.descricao", required = false) String descricao,
            @RequestParam(name = "sensor.localizacao", required = false) String localizacao,
            @RequestParam(name = "projeto.projeto", required = false) Projeto projeto
    ) {
        var sensor = Sensor.builder()
                .id(id)
                .tipoSensor(tipoSensor)
                .descricao(descricao)
                .localizacao(localizacao)
                .projeto(projeto)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Sensor> example = Example.of(sensor, matcher);
        Collection<Sensor> sensores = service.findAll(example);

        var response = sensores.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }
}

