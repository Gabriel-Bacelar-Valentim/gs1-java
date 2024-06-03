package br.fiap.com.global_solution_java.service;

import br.fiap.com.global_solution_java.dto.request.ParceiroRequest;
import br.fiap.com.global_solution_java.dto.response.ParceiroResponse;
import br.fiap.com.global_solution_java.entity.Parceiro;
import br.fiap.com.global_solution_java.repository.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class ParceiroService implements ServiceDTO<Parceiro, ParceiroRequest, ParceiroResponse>{

    @Autowired
    private ParceiroRepository repo;

    @Override
    public Collection<Parceiro> findAll(Example<Parceiro> example) {
        return repo.findAll(example);
    }

    @Override
    public Parceiro findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Parceiro save(Parceiro e) {
        return repo.save(e);
    }

    @Override
    public Parceiro toEntity(ParceiroRequest dto) {
        if (Objects.isNull( dto )) return null;

        return Parceiro.builder()
                .nomeParceiro(dto.nomeParceiro())
                .tipoParceiro(dto.tipoParceiro())
                .contato(dto.contato())
                .email(dto.email())
                .telefone(dto.telefone())
                .build();
    }

    @Override
    public ParceiroResponse toResponse(Parceiro e) {
        return ParceiroResponse.builder()
                .id(e.getId())
                .nomeParceiro(e.getNomeParceiro())
                .tipoParceiro(e.getTipoParceiro())
                .contato(e.getContato())
                .email(e.getEmail())
                .telefone(e.getTelefone())
                .build();
    }
}
