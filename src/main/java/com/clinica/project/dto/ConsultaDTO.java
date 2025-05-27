package com.clinica.project.dto;

import java.time.LocalDateTime;

public class ConsultaDTO {

    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private LocalDateTime dataHora;

    public ConsultaDTO() {

    }

    public ConsultaDTO(Long id, Long pacienteId, Long medicoId, LocalDateTime dataHora) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
    }

    public ConsultaDTO(com.clinica.project.entites.Consulta consulta) {
        this.id = consulta.getId();
        this.pacienteId = consulta.getPaciente().getId();
        this.medicoId = consulta.getMedico().getId();
        this.dataHora = consulta.getDataHora();
    }

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getPacienteId()
    {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId)
    {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId()
    {
        return medicoId;
    }
    public void setMedicoId(Long medicoId)
    {
        this.medicoId = medicoId;
    }

    public LocalDateTime getDataHora()
    {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora)
    {
        this.dataHora = dataHora;
    }
}

