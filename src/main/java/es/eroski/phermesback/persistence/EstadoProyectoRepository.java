package es.eroski.phermesback.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.EstadoProyectoEntity;


@Repository
public interface EstadoProyectoRepository extends JpaRepository<EstadoProyectoEntity, Integer>{
}


