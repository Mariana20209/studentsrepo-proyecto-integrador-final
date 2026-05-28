package com.grupo7.studentsrepo.mapper;

import com.grupo7.studentsrepo.dto.request.DocumentoRequestDTO;
import com.grupo7.studentsrepo.dto.response.DocumentoResponseDTO;

import com.grupo7.studentsrepo.model.entity.Documento;
import com.grupo7.studentsrepo.model.entity.Estudiante;

public class DocumentoMapper {

    // RequestDTO -> Entity
    public static Documento toEntity(DocumentoRequestDTO dto) {

        Documento documento = new Documento();

        documento.setTitulo(dto.getTitulo());
        documento.setDescripcion(dto.getDescripcion());
        documento.setCategoria(dto.getCategoria());
        documento.setNombreArchivo(dto.getNombreArchivo());

        Estudiante estudiante = new Estudiante();

        estudiante.setId(dto.getEstudianteId());

        documento.setEstudiante(estudiante);

        return documento;
    }

    // Entity -> ResponseDTO
    public static DocumentoResponseDTO toResponseDTO(
            Documento documento
    ) {

        DocumentoResponseDTO dto =
                new DocumentoResponseDTO();

        dto.setId(documento.getId());

        dto.setTitulo(documento.getTitulo());

        dto.setDescripcion(documento.getDescripcion());

        dto.setCategoria(documento.getCategoria());

        dto.setEstado(documento.getEstado());

        dto.setNombreArchivo(documento.getNombreArchivo());

        dto.setEstudianteNombre(

                documento.getEstudiante()
                        .getNombreCompleto()
                        .getNombres()

                        + " " +

                documento.getEstudiante()
                        .getNombreCompleto()
                        .getApellidos()
        );

        return dto;
    }

}