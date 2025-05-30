package com.clinica.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinica.project.dto.MedicoDTO;
import com.clinica.project.service.MedicoService;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listarMedicos() {
        List<MedicoDTO> medicos = medicoService.listarTodos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> buscarPorId(@PathVariable Long id) {
        MedicoDTO medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(medico);
    }

    @PostMapping("/new")
    public ResponseEntity<MedicoDTO> criarMedico(@RequestBody MedicoDTO dto) {
        MedicoDTO novoMedico = medicoService.salvar(dto);
        return ResponseEntity.ok(novoMedico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedico(@PathVariable Long id, @RequestBody MedicoDTO dto) {
        dto.setId(id);
        MedicoDTO medicoAtualizado = medicoService.salvar(dto);
        return ResponseEntity.ok(medicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarMedico(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.ok("Médico com ID " + id + " foi excluído com sucesso.");
    }

}

