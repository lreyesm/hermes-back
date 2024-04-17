package es.eroski.phermesback.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.EstadoEscenarioEntity;

@Repository
public interface EstadoEscenarioRepository extends JpaRepository<EstadoEscenarioEntity, Integer>{
}
