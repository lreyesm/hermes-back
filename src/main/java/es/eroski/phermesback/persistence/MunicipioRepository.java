package es.eroski.phermesback.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.MunicipioEntity;
import es.eroski.phermesback.model.ProvinciaEntity;

@Repository
public interface MunicipioRepository extends JpaRepository<MunicipioEntity, Integer> {
	
	List<MunicipioEntity> findByProvinciaIn(List<ProvinciaEntity> provincia);

	MunicipioEntity findByIdAndProvincia(Integer id, ProvinciaEntity provincia);

}
