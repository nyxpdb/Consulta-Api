package com.clinica.project.dto;

import jakarta.persistence.*;

import javax.management.relation.Role;

public record UsuarioDTO(
                                  String nome,
                                  String email,
                                  String senha,
                                  Role role) {
}
