package es.eroski.phermesback.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.ComunidadAutonomaEntity;

@Repository
public interface ComunidadAutonomaRepository extends JpaRepository<ComunidadAutonomaEntity, Integer> {
	List<ComunidadAutonomaEntity> findByIdIn(List<Integer> id);
}
