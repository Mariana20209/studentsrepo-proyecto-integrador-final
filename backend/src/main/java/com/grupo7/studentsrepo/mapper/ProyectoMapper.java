package com.grupo7.studentsrepo.mapper;

import com.grupo7.studentsrepo.dto.request.ProyectoRequestDTO;
import com.grupo7.studentsrepo.dto.response.ProyectoResponseDTO;

import com.grupo7.studentsrepo.model.entity.Proyecto;
import com.grupo7.studentsrepo.model.entity.Estudiante;

public class ProyectoMapper {

    // RequestDTO -> Entity
    public static Proyecto toEntity(ProyectoRequestDTO dto) {

        Proyecto proyecto = new Proyecto();

        proyecto.setTitulo(dto.getTitulo());
        proyecto.setDescripcion(dto.getDescripcion());
        proyecto.setCategoria(dto.getCategoria());
        proyecto.setNombreArchivo(dto.getNombreArchivo());

        Estudiante estudiante = new Estudiante();

        estudiante.setId(dto.getEstudianteId());

        proyecto.setEstudiante(estudiante);

        return proyecto;
    }

    // Entity -> ResponseDTO
    public static ProyectoResponseDTO toResponseDTO(
            Proyecto proyecto
    ) {

        ProyectoResponseDTO dto =
                new ProyectoResponseDTO();

        dto.setId(proyecto.getId());

        dto.setTitulo(proyecto.getTitulo());

        dto.setDescripcion(proyecto.getDescripcion());

        dto.setCategoria(proyecto.getCategoria());

        dto.setEstado(proyecto.getEstado());

        dto.setNombreArchivo(proyecto.getNombreArchivo());

        dto.setEstudianteNombre(

                proyecto.getEstudiante()
                        .getNombreCompleto()
                        .getNombres()

                        + " " +

                proyecto.getEstudiante()
                        .getNombreCompleto()
                        .getApellidos()
        );

        return dto;
    }

}