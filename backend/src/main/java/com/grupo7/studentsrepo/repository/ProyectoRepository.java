package com.grupo7.studentsrepo.repository;

import com.grupo7.studentsrepo.model.entity.Proyecto;
import com.grupo7.studentsrepo.model.enums.CategoriaProyecto;
import com.grupo7.studentsrepo.model.enums.EstadoProyecto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    List<Proyecto> findByEstudiante_Id(Long estudianteId);

    List<Proyecto> findByEstudiante_IdAndCategoria(Long estudianteId,
                                                    CategoriaProyecto categoria);

    List<Proyecto> findByEstudiante_IdAndEstado(Long estudianteId,
                                                 EstadoProyecto estado);

    List<Proyecto> findByCategoria(CategoriaProyecto categoria);

    List<Proyecto> findByEstado(EstadoProyecto estado);

}
