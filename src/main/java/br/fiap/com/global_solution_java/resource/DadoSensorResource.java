package br.fiap.com.global_solution_java.resource;

import br.fiap.com.global_solution_java.dto.request.DadoSensorRequest;

import br.fiap.com.global_solution_java.dto.response.DadoSensorResponse;

import br.fiap.com.global_solution_java.entity.DadoSensor;
import br.fiap.com.global_solution_java.entity.Sensor;
import br.fiap.com.global_solution_java.service.DadoSensorService;

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
@RequestMapping("/dadossensores")
public class DadoSensorResource implements ResourceDTO<DadoSensorRequest, DadoSensorResponse> {

    @Autowired
    private DadoSensorService service;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<DadoSensorResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<DadoSensorResponse> save(@RequestBody @Valid DadoSensorRequest r) {
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
    public ResponseEntity<Collection<DadoSensorResponse>> findAll(
            @RequestParam(name = "dadossensor.id", required = false) Long id,
            @RequestParam(name = "dadossensor.temperatura", required = false) Double temperatura,
            @RequestParam(name = "dadossensor.salinidade", required = false) Double salinidade,
            @RequestParam(name = "dadossensor.nivelPh", required = false) Double nivelPh,
            @RequestParam(name = "dadossensor.localizacao", required = false) String localizacao,
            @RequestParam(name = "sensor.sensor", required = false) Sensor sensor
    ) {
        var dadosSensor = DadoSensor.builder()
                .id(id)
                .temperatura(temperatura)
                .salinidade(salinidade)
                .nivelPh(nivelPh)
                .localizacao(localizacao)
                .sensor(sensor)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<DadoSensor> example = Example.of(dadosSensor, matcher);
        Collection<DadoSensor> dadosSensores = service.findAll(example);

        var response = dadosSensores.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }
}
