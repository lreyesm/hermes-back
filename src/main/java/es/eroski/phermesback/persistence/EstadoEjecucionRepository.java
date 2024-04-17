package es.eroski.phermesback.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.EstadoEjecucionEntity;

@Repository
public interface EstadoEjecucionRepository extends JpaRepository<EstadoEjecucionEntity, Integer> {
	
}
