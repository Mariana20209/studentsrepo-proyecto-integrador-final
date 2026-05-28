package com.grupo7.studentsrepo.dto.response;

import com.grupo7.studentsrepo.model.enums.CategoriaDocumento;
import com.grupo7.studentsrepo.model.enums.EstadoDocumento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DocumentoResponseDTO {

    private Long id;

    private String titulo;

    private String descripcion;

    private CategoriaDocumento categoria;

    private EstadoDocumento estado;

    private String nombreArchivo;

    private String estudianteNombre;

}