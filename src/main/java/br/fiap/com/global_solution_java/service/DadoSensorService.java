package br.fiap.com.global_solution_java.service;


import br.fiap.com.global_solution_java.dto.request.DadoSensorRequest;
import br.fiap.com.global_solution_java.dto.response.DadoSensorResponse;
import br.fiap.com.global_solution_java.entity.DadoSensor;
import br.fiap.com.global_solution_java.entity.Sensor;
import br.fiap.com.global_solution_java.repository.DadoSersorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class DadoSensorService implements ServiceDTO<DadoSensor, DadoSensorRequest, DadoSensorResponse>{

    @Autowired
    private DadoSersorRepository repo;

    @Autowired
    private SensorService sensorService;

    @Override
    public Collection<DadoSensor> findAll(Example<DadoSensor> example) {
        return repo.findAll( example );
    }

    @Override
    public DadoSensor findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public DadoSensor save(DadoSensor e) {
        return repo.save( e );
    }

    @Override
    public DadoSensor toEntity(DadoSensorRequest dto) {
        if (Objects.isNull( dto )) return null;

        var sensor = this.findById(dto.sensor().id());

        return DadoSensor.builder()
                .temperatura(dto.temperatura())
                .salinidade(dto.salinidade())
                .nivelPh(dto.nivelPh())
                .localizacao(dto.localizacao())
                .sensor( sensor.getSensor())
                .build();
    }

    @Override
    public DadoSensorResponse toResponse(DadoSensor e) {
        return DadoSensorResponse.builder()
                .id(e.getId())
                .temperatura(e.getTemperatura())
                .salinidade(e.getSalinidade())
                .nivelPh(e.getNivelPh())
                .localizacao(e.getLocalizacao())
                .sensor( sensorService.toResponse(Sensor.builder().build()))
                .build();
    }
}
