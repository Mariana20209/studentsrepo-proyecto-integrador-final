package com.grupo7.studentsrepo.dto.request;

import com.grupo7.studentsrepo.model.enums.CategoriaDocumento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DocumentoRequestDTO {

    private String titulo;

    private String descripcion;

    private CategoriaDocumento categoria;

    private String nombreArchivo;

    private Long estudianteId;

}