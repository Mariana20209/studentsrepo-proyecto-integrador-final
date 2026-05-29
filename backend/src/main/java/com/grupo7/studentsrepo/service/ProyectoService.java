package com.grupo7.studentsrepo.service;

import com.grupo7.studentsrepo.model.entity.Proyecto;
import com.grupo7.studentsrepo.model.entity.Estudiante;
import com.grupo7.studentsrepo.model.enums.CategoriaProyecto;
import com.grupo7.studentsrepo.model.enums.EstadoProyecto;
import com.grupo7.studentsrepo.repository.ProyectoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private EstudianteService estudianteService;

    public List<Proyecto> findAll() {
        return proyectoRepository.findAll();
    }

    public Proyecto findById(Long id) {
        return findEntity(id);
    }

    public List<Proyecto> findByEstudiante(Long estudianteId) {

        return proyectoRepository
                .findByEstudiante_Id(estudianteId);
    }

    public List<Proyecto> findByCategoria(CategoriaProyecto categoria) {

        return proyectoRepository.findByCategoria(categoria);
    }

    public List<Proyecto> findByEstado(EstadoProyecto estado) {

        return proyectoRepository.findByEstado(estado);
    }

    public List<Proyecto> findByEstudianteYCategoria(
            Long estudianteId,
            CategoriaProyecto categoria
    ) {

        return proyectoRepository
                .findByEstudiante_IdAndCategoria(estudianteId, categoria);
    }

    @Transactional
    public Proyecto save(Proyecto request) {

        Estudiante estudiante = estudianteService
                .findEntity(request.getEstudiante().getId());

        request.setEstudiante(estudiante);

        request.setEstado(EstadoProyecto.PENDIENTE);

        return proyectoRepository.save(request);
    }

    @Transactional
    public Proyecto cambiarEstado(Long id,
                                   EstadoProyecto nuevoEstado) {

        Proyecto proyecto = findEntity(id);

        proyecto.setEstado(nuevoEstado);

        return proyectoRepository.save(proyecto);
    }

    @Transactional
    public Proyecto update(Long id,
                            Proyecto request) {

        Proyecto proyecto = findEntity(id);

        proyecto.setTitulo(request.getTitulo());
        proyecto.setDescripcion(request.getDescripcion());
        proyecto.setCategoria(request.getCategoria());
        proyecto.setNombreArchivo(request.getNombreArchivo());

        return proyectoRepository.save(proyecto);
    }

    @Transactional
    public void delete(Long id) {

        findEntity(id);

        proyectoRepository.deleteById(id);
    }

    public Proyecto findEntity(Long id) {

        return proyectoRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Proyecto no encontrado con id: " + id
                        ));
    }

}
