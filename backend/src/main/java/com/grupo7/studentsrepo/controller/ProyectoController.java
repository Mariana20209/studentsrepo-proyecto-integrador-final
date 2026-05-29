package com.grupo7.studentsrepo.controller;

import com.grupo7.studentsrepo.dto.request.ProyectoRequestDTO;
import com.grupo7.studentsrepo.dto.response.ProyectoResponseDTO;

import com.grupo7.studentsrepo.mapper.ProyectoMapper;

import com.grupo7.studentsrepo.model.entity.Proyecto;

import com.grupo7.studentsrepo.model.enums.CategoriaProyecto;
import com.grupo7.studentsrepo.model.enums.EstadoProyecto;

import com.grupo7.studentsrepo.service.ProyectoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")

@Tag(name = "Proyectos", description = "Gestión de proyectos académicos")

public class ProyectoController {

        @Autowired
        private ProyectoService proyectoService;

        @Operation(summary = "Obtener todos los proyectos")
        @GetMapping
        public List<ProyectoResponseDTO> getAll() {

                return proyectoService.findAll().stream()
                        .map(ProyectoMapper::toResponseDTO)
                        .toList();
        }

        @Operation(summary = "Obtener proyecto por ID")
        @GetMapping("/{id}")
        public ProyectoResponseDTO getById(
                        @PathVariable Long id) {

                Proyecto documento = proyectoService.findById(id);

                return ProyectoMapper.toResponseDTO(documento);
        }

        @Operation(summary = "Filtrar por categoría")
        @GetMapping("/categoria/{categoria}")

        public List<ProyectoResponseDTO> getByCategoria(
                        @PathVariable CategoriaProyecto categoria) {

                return proyectoService.findByCategoria(categoria).stream()
                        .map(ProyectoMapper::toResponseDTO)
                        .toList();
        }

        @Operation(summary = "Filtrar por estado")
        @GetMapping("/estado/{estado}")

        public List<ProyectoResponseDTO> getByEstado(
                        @PathVariable EstadoProyecto estado) {

                return proyectoService.findByEstado(estado).stream()
                        .map(ProyectoMapper::toResponseDTO)
                        .toList();
        }

        @Operation(summary = "Proyectos de un estudiante")
        @GetMapping("/estudiante/{estudianteId}")

        public List<ProyectoResponseDTO> getByEstudiante(
                        @PathVariable Long estudianteId) {

                return proyectoService.findByEstudiante(estudianteId).stream()
                        .map(ProyectoMapper::toResponseDTO)
                        .toList();
        }

        @Operation(summary = "Proyectos de un estudiante por categoría")
        @GetMapping("/estudiante/{estudianteId}/categoria/{categoria}")

        public List<ProyectoResponseDTO> getByEstudianteYCategoria(
                        @PathVariable Long estudianteId,
                        @PathVariable CategoriaProyecto categoria) {

                return proyectoService
                                .findByEstudianteYCategoria(estudianteId, categoria)
                                .stream()
                                .map(ProyectoMapper::toResponseDTO)
                                .toList();
        }

        @Operation(summary = "Crear proyecto")
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)

        public ProyectoResponseDTO create(
                        @RequestBody ProyectoRequestDTO requestDTO) {

                Proyecto proyecto = ProyectoMapper.toEntity(requestDTO);

                Proyecto saved = proyectoService.save(proyecto);

                return ProyectoMapper.toResponseDTO(saved);
        }

        @Operation(summary = "Cambiar estado del proyecto")
        @PatchMapping("/{id}/estado/{estado}")

        public ProyectoResponseDTO cambiarEstado(
                        @PathVariable Long id,
                        @PathVariable EstadoProyecto estado) {

                Proyecto proyecto = proyectoService.cambiarEstado(id, estado);

                return ProyectoMapper.toResponseDTO(proyecto);
        }

        @Operation(summary = "Actualizar proyecto")
        @PutMapping("/{id}")

        public ProyectoResponseDTO update(
                        @PathVariable Long id,
                        @RequestBody ProyectoRequestDTO requestDTO) {

                Proyecto proyecto = ProyectoMapper.toEntity(requestDTO);

                Proyecto updated = proyectoService.update(id, proyecto);

                return ProyectoMapper.toResponseDTO(updated);
        }

        @Operation(summary = "Eliminar proyecto")
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)

        public void delete(@PathVariable Long id) {

                proyectoService.delete(id);
        }

}
