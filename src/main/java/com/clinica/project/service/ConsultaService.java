package com.clinica.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.project.dto.ConsultaDTO;
import com.clinica.project.entites.Consulta;
import com.clinica.project.entites.Medico;
import com.clinica.project.entites.Paciente;
import com.clinica.project.repository.ConsultaRepository;
import com.clinica.project.repository.MedicoRepository;
import com.clinica.project.repository.PacienteRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<ConsultaDTO> listarTodos() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream()
                .map(consulta -> new ConsultaDTO(consulta))
                .collect(Collectors.toList());
    }

    public ConsultaDTO buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .map(consulta -> new ConsultaDTO(consulta))
                .orElseThrow(() -> new RuntimeException("Consulta com ID " + id + " não encontrada."));
    }

    public ConsultaDTO salvar(ConsultaDTO dto) {
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico com ID " + dto.getMedicoId() + " não encontrado."));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente com ID " + dto.getPacienteId() + " não encontrado."));

        LocalDateTime inicio = dto.getDataHora();
        LocalDateTime fim = inicio.plusMinutes(30);

        boolean medicoOcupado = consultaRepository.existsByMedicoIdAndDataHoraBetween(medico.getId(), inicio, fim);
        if (medicoOcupado) {
            throw new RuntimeException("Médico já possui consulta agendada entre " + inicio + " e " + fim + ".");
        }

        Consulta consulta = new Consulta();
        consulta.setId(dto.getId());
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataHora(inicio);

        consulta = consultaRepository.save(consulta);
        return new ConsultaDTO(consulta);
    }

    public void deletar(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta com ID " + id + " não encontrada para exclusão.");
        }
        consultaRepository.deleteById(id);
    }
}
