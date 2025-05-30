package com.clinica.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.project.dto.MedicoDTO;
import com.clinica.project.entites.Medico;
import com.clinica.project.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<MedicoDTO> listarTodos() {
        List<Medico> medicos = medicoRepository.findAll();
        return medicos.stream().map(MedicoDTO::new).collect(Collectors.toList());
    }

    public MedicoDTO buscarPorId(Long id) {
        return medicoRepository.findById(id)
                .map(MedicoDTO::new)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
    }

    public MedicoDTO salvar(MedicoDTO dto) {
        Medico medico = new Medico();
        medico.setId(dto.getId());
        medico.setNome(dto.getNome());
        medico.setEmail(dto.getEmail());
        medico.setTelefone(dto.getTelefone());
        medico.setEspecialidade(dto.getEspecialidade());

        medico = medicoRepository.save(medico);
        return new MedicoDTO(medico);
    }

    public void deletar(Long id) {
        medicoRepository.deleteById(id);
    }
}

