package es.eroski.phermesback.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.RotuloEntity;

@Repository
public interface RotuloRepository extends JpaRepository<RotuloEntity, Integer>{
}