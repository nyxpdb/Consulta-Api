package com.clinica.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.project.dto.PacienteDTO;
import com.clinica.project.entites.Paciente;
import com.clinica.project.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<PacienteDTO> listarTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(PacienteDTO::new).collect(Collectors.toList());
    }

    public PacienteDTO buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .map(PacienteDTO::new)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public PacienteDTO salvar(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setId(dto.getId());
        paciente.setNome(dto.getNome());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefone(dto.getTelefone());

        paciente = pacienteRepository.save(paciente);
        return new PacienteDTO(paciente);
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}

