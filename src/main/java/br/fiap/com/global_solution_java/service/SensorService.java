package br.fiap.com.global_solution_java.service;


import br.fiap.com.global_solution_java.dto.request.SensorRequest;
import br.fiap.com.global_solution_java.dto.response.SensorResponse;
import br.fiap.com.global_solution_java.entity.Projeto;
import br.fiap.com.global_solution_java.entity.Sensor;
import br.fiap.com.global_solution_java.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class SensorService implements ServiceDTO<Sensor, SensorRequest, SensorResponse>{

    @Autowired
    private SensorRepository repo;

    @Autowired
    private ProjetoService projetoService;

    @Override
    public Collection<Sensor> findAll(Example<Sensor> example) {
        return repo.findAll(example);
    }

    @Override
    public Sensor findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Sensor save(Sensor e) {
        return repo.save(e);
    }

    @Override
    public Sensor toEntity(SensorRequest dto) {
        if (Objects.isNull( dto )) return null;

        var projeto = this.findById(dto.projeto().id());

        return Sensor.builder()
                .tipoSensor(dto.tipoSensor())
                .descricao(dto.descricao())
                .localizacao(dto.localizacao())
                .dataInstalacao(dto.dataInstalacao())
                .projeto( projeto.getProjeto())
                .build();
    }

    @Override
    public SensorResponse toResponse(Sensor e) {
        return SensorResponse.builder()
                .id(e.getId())
                .tipoSensor(e.getTipoSensor())
                .descricao(e.getDescricao())
                .localizacao(e.getLocalizacao())
                .dataInstalacao(e.getDataInstalacao())
                .projeto( projetoService.toResponse(Projeto.builder().build()))
                .build();
    }
}
