package com.grupo7.studentsrepo.dto.request;

import com.grupo7.studentsrepo.model.enums.CategoriaProyecto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProyectoRequestDTO {

    private String titulo;

    private String descripcion;

    private CategoriaProyecto categoria;

    private String nombreArchivo;

    private Long estudianteId;

}