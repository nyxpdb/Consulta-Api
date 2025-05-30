package com.clinica.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinica.project.dto.ConsultaDTO;
import com.clinica.project.service.ConsultaService;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listarConsultas() {
        List<ConsultaDTO> consultas = consultaService.listarTodos();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> buscarPorId(@PathVariable Long id) {
        ConsultaDTO consulta = consultaService.buscarPorId(id);
        return ResponseEntity.ok(consulta);
    }

    @PostMapping("/new")
    public ResponseEntity<ConsultaDTO> criarConsulta(@RequestBody ConsultaDTO dto) {
        ConsultaDTO novaConsulta = consultaService.salvar(dto);
        return ResponseEntity.ok(novaConsulta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarConsulta(@PathVariable Long id) {
        consultaService.deletar(id);
        return ResponseEntity.ok("Consulta com ID " + id + " foi excluída com sucesso.");
    }
}
