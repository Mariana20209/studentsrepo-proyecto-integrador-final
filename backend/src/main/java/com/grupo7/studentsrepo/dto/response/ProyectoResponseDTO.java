package com.grupo7.studentsrepo.dto.response;

import com.grupo7.studentsrepo.model.enums.CategoriaProyecto;
import com.grupo7.studentsrepo.model.enums.EstadoProyecto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProyectoResponseDTO {

    private Long id;

    private String titulo;

    private String descripcion;

    private CategoriaProyecto categoria;

    private EstadoProyecto estado;

    private String nombreArchivo;

    private String estudianteNombre;

}