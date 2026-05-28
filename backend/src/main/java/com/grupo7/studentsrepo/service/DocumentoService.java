package com.grupo7.studentsrepo.service;

import com.grupo7.studentsrepo.model.entity.Documento;
import com.grupo7.studentsrepo.model.entity.Estudiante;
import com.grupo7.studentsrepo.model.enums.CategoriaDocumento;
import com.grupo7.studentsrepo.model.enums.EstadoDocumento;
import com.grupo7.studentsrepo.repository.DocumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private EstudianteService estudianteService;

    public List<Documento> findAll() {
        return documentoRepository.findAll();
    }

    public Documento findById(Long id) {
        return findEntity(id);
    }

    public List<Documento> findByEstudiante(Long estudianteId) {

        return documentoRepository
                .findByEstudiante_Id(estudianteId);
    }

    public List<Documento> findByCategoria(CategoriaDocumento categoria) {

        return documentoRepository.findByCategoria(categoria);
    }

    public List<Documento> findByEstado(EstadoDocumento estado) {

        return documentoRepository.findByEstado(estado);
    }

    public List<Documento> findByEstudianteYCategoria(
            Long estudianteId,
            CategoriaDocumento categoria
    ) {

        return documentoRepository
                .findByEstudiante_IdAndCategoria(estudianteId, categoria);
    }

    @Transactional
    public Documento save(Documento request) {

        Estudiante estudiante = estudianteService
                .findEntity(request.getEstudiante().getId());

        request.setEstudiante(estudiante);

        request.setEstado(EstadoDocumento.PENDIENTE);

        return documentoRepository.save(request);
    }

    @Transactional
    public Documento cambiarEstado(Long id,
                                   EstadoDocumento nuevoEstado) {

        Documento documento = findEntity(id);

        documento.setEstado(nuevoEstado);

        return documentoRepository.save(documento);
    }

    @Transactional
    public Documento update(Long id,
                            Documento request) {

        Documento documento = findEntity(id);

        documento.setTitulo(request.getTitulo());
        documento.setDescripcion(request.getDescripcion());
        documento.setCategoria(request.getCategoria());
        documento.setNombreArchivo(request.getNombreArchivo());

        return documentoRepository.save(documento);
    }

    @Transactional
    public void delete(Long id) {

        findEntity(id);

        documentoRepository.deleteById(id);
    }

    public Documento findEntity(Long id) {

        return documentoRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Documento no encontrado con id: " + id
                        ));
    }

}
