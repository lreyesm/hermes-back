package es.eroski.phermesback.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.EstadoCentroEntity;

@Repository
public interface EstadoCentroRepository extends JpaRepository<EstadoCentroEntity, Integer> {
	
}
