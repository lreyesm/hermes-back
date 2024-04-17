package es.eroski.phermesback.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.ComunidadAutonomaEntity;
import es.eroski.phermesback.model.ProvinciaEntity;

@Repository
public interface ProvinciaRepository extends JpaRepository<ProvinciaEntity, Integer> {
	

	List<ProvinciaEntity> findByComunidadAutonomaIn(List<ComunidadAutonomaEntity> cA);

}
