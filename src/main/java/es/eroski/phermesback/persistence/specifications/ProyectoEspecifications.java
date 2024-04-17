package es.eroski.phermesback.persistence.specifications;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import es.eroski.phermesback.dto.Proyecto;
import es.eroski.phermesback.model.ComunidadAutonomaEntity;
import es.eroski.phermesback.model.MunicipioEntity;
import es.eroski.phermesback.model.ProvinciaEntity;
import es.eroski.phermesback.model.ProyectoEntity;
import jakarta.persistence.criteria.Join;

public class ProyectoEspecifications {

	private static Specification<ProyectoEntity> byEstado(Integer estado) {
        return (root, query, cb) -> cb.equal(root.get("estado").get("id"), estado);
    }
   
	private static Specification<ProyectoEntity> byName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" +   name.toLowerCase() + "%");
    }
   
	private static Specification<ProyectoEntity> byId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
	
	private static Specification<ProyectoEntity> byCreationDate(Date startDate, Date endDate) {
        return (root, query, cb) -> cb.between(root.get("auditable").get("insertDate"), startDate, endDate );
    }
   
	private static Specification<ProyectoEntity> byComunidadAutonoma(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, ComunidadAutonomaEntity> comunidadAutonomaJoin = root.join("comunidadAutonoma");
            return comunidadAutonomaJoin.get("id").in(list);
        };
    }
   
	private static Specification<ProyectoEntity> byMunicipio(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, MunicipioEntity> municipioJoin = root.join("municipio");
            return municipioJoin.get("id").in(list);
        };
    }
   
	private static Specification<ProyectoEntity> byProvincia(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, ProvinciaEntity> provinciaJoin = root.join("provincia");
            return provinciaJoin.get("id").in(list);
        };
    }
	
    public static Specification<ProyectoEntity> byBajaDateIsNull() {
        return (root, query, cb) -> cb.isNull(root.get("bajaDate"));
    }
	   
   public static Specification<ProyectoEntity> buildSpecification(String name,
		   Integer estado, Long id,List<Integer> comunidades,List<Integer> municipios,List<Integer> provincias, 
		   Date startCreationDate, Date endCreationDate) {
	   
		Specification<ProyectoEntity> specification = Specification.where(null);
		
		if (name != null) {
		specification = specification.and(byName(name));
		}
		if (estado != null) {
		specification = specification.and(byEstado(estado));
		}
		if (id != null) {
		specification = specification.and(byId(id));
		}
		if (comunidades != null && !comunidades.isEmpty()) {
		specification = specification.and(byComunidadAutonoma(comunidades));
		}
		if (municipios != null && !municipios.isEmpty()) {
		specification = specification.and(byMunicipio(municipios));
		}
		if (provincias != null && !provincias.isEmpty()) {
		specification = specification.and(byProvincia(provincias));
		}
		if (startCreationDate != null && endCreationDate != null ) {
		specification = specification.and(byCreationDate(startCreationDate, endCreationDate));
		}
		
		specification.and(byBajaDateIsNull());
		
		return specification;
	}

}
