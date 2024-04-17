package es.eroski.phermesback.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.eroski.phermesback.model.CentroFicticioEntity;
import es.eroski.phermesback.model.EjecucionEntity;

@Repository
public interface CentroFicticioRepository extends JpaRepository<CentroFicticioEntity, Long> {
	
	Page<CentroFicticioEntity> findAll(Specification<CentroFicticioEntity> spec, Pageable page);
	
	long count();
}
