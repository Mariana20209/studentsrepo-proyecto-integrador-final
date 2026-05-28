package com.grupo7.studentsrepo.repository;

import com.grupo7.studentsrepo.model.entity.Documento;
import com.grupo7.studentsrepo.model.enums.CategoriaDocumento;
import com.grupo7.studentsrepo.model.enums.EstadoDocumento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    List<Documento> findByEstudiante_Id(Long estudianteId);

    List<Documento> findByEstudiante_IdAndCategoria(Long estudianteId,
                                                    CategoriaDocumento categoria);

    List<Documento> findByEstudiante_IdAndEstado(Long estudianteId,
                                                 EstadoDocumento estado);

    List<Documento> findByCategoria(CategoriaDocumento categoria);

    List<Documento> findByEstado(EstadoDocumento estado);

}
