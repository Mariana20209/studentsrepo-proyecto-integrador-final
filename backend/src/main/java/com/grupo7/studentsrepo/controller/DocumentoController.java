package com.grupo7.studentsrepo.controller;

import com.grupo7.studentsrepo.dto.request.DocumentoRequestDTO;
import com.grupo7.studentsrepo.dto.response.DocumentoResponseDTO;

import com.grupo7.studentsrepo.mapper.DocumentoMapper;

import com.grupo7.studentsrepo.model.entity.Documento;

import com.grupo7.studentsrepo.model.enums.CategoriaDocumento;
import com.grupo7.studentsrepo.model.enums.EstadoDocumento;

import com.grupo7.studentsrepo.service.DocumentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")

@Tag(name = "Documentos", description = "Gestión de documentos académicos")

public class DocumentoController {

        @Autowired
        private DocumentoService documentoService;

        @Operation(summary = "Obtener todos los documentos")
        @GetMapping
        public List<DocumentoResponseDTO> getAll() {

                return documentoService.findAll().stream()
                        .map(DocumentoMapper::toResponseDTO)
                        .toList();
        }

        @Operation(summary = "Obtener documento por ID")
        @GetMapping("/{id}")
        public DocumentoResponseDTO getById(
                        @PathVariable Long id) {

                Documento documento = documentoService.findById(id);

                return DocumentoMapper.toResponseDTO(documento);
        }

        @Operation(summary = "Filtrar por categoría")
        @GetMapping("/categoria/{categoria}")

        public List<DocumentoResponseDTO> getByCategoria(
                        @PathVariable CategoriaDocumento categoria) {

                return documentoService.findByCategoria(categoria).stream()
                        .map(DocumentoMapper::toResponseDTO)
                        .toList();
        }

        @Operation(summary = "Filtrar por estado")
        @GetMapping("/estado/{estado}")

        public List<DocumentoResponseDTO> getByEstado(
                        @PathVariable EstadoDocumento estado) {

                return documentoService.findByEstado(estado).stream()
                        .map(DocumentoMapper::toResponseDTO)
                        .toList();
        }

        @Operation(summary = "Documentos de un estudiante")
        @GetMapping("/estudiante/{estudianteId}")

        public List<DocumentoResponseDTO> getByEstudiante(
                        @PathVariable Long estudianteId) {

                return documentoService.findByEstudiante(estudianteId).stream()
                        .map(DocumentoMapper::toResponseDTO)
                        .toList();
        }

        @Operation(summary = "Documentos de un estudiante por categoría")
        @GetMapping("/estudiante/{estudianteId}/categoria/{categoria}")

        public List<DocumentoResponseDTO> getByEstudianteYCategoria(
                        @PathVariable Long estudianteId,
                        @PathVariable CategoriaDocumento categoria) {

                return documentoService
                                .findByEstudianteYCategoria(estudianteId, categoria)
                                .stream()
                                .map(DocumentoMapper::toResponseDTO)
                                .toList();
        }

        @Operation(summary = "Crear documento")
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)

        public DocumentoResponseDTO create(
                        @RequestBody DocumentoRequestDTO requestDTO) {

                Documento documento = DocumentoMapper.toEntity(requestDTO);

                Documento saved = documentoService.save(documento);

                return DocumentoMapper.toResponseDTO(saved);
        }

        @Operation(summary = "Cambiar estado del documento")
        @PatchMapping("/{id}/estado/{estado}")

        public DocumentoResponseDTO cambiarEstado(
                        @PathVariable Long id,
                        @PathVariable EstadoDocumento estado) {

                Documento documento = documentoService.cambiarEstado(id, estado);

                return DocumentoMapper.toResponseDTO(documento);
        }

        @Operation(summary = "Actualizar documento")
        @PutMapping("/{id}")

        public DocumentoResponseDTO update(
                        @PathVariable Long id,
                        @RequestBody DocumentoRequestDTO requestDTO) {

                Documento documento = DocumentoMapper.toEntity(requestDTO);

                Documento updated = documentoService.update(id, documento);

                return DocumentoMapper.toResponseDTO(updated);
        }

        @Operation(summary = "Eliminar documento")
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)

        public void delete(@PathVariable Long id) {

                documentoService.delete(id);
        }

}
