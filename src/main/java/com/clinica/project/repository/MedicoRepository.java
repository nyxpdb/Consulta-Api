package com.clinica.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.clinica.project.entites.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>
{

}

