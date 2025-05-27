package com.clinica.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.clinica.project.entites.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>
{

}

