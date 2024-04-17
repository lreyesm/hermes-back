package es.eroski.phermesback.persistence.specifications;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import es.eroski.phermesback.dto.Proyecto;
import es.eroski.phermesback.model.ComunidadAutonomaEntity;
import es.eroski.phermesback.model.MunicipioEntity;
import es.eroski.phermesback.model.ProvinciaEntity;
import es.eroski.phermesback.model.EjecucionEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

public class EjecucionEspecification {

	private static Specification<EjecucionEntity> byEstado(Integer estado) {
        return (root, query, cb) -> cb.equal(root.get("estado").get("id"), estado);
    }
	
	private static Specification<EjecucionEntity> byProyecto(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("escenario").get("proyecto").get("name")), "%" +   name.toLowerCase() + "%");
    }
	
	private static Specification<EjecucionEntity> byIdProyecto(Long id) {
        return (root, query, cb) -> cb.equal(root.get("escenario").get("proyecto").get("id"), id);
    }
	
	private static Specification<EjecucionEntity> byEscenario(Long id) {
        return (root, query, cb) -> cb.equal(root.get("escenario").get("id"), id);
    }
	
	private static Specification<EjecucionEntity> byUser(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("auditable").get("insertUser").get("username")), "%" +   name.toLowerCase() + "%");
    }
	   
	private static Specification<EjecucionEntity> byName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("escenario").get("name")), "%" +   name.toLowerCase() + "%");
    }
   
	private static Specification<EjecucionEntity> byId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
	
	private static Specification<EjecucionEntity> byExecutionDate(Date startDate, Date endDate) {
        return (root, query, cb) -> cb.between(root.get("auditable").get("insertDate"), startDate, endDate );
    }
   
	private static Specification<EjecucionEntity> byComunidadAutonoma(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, ComunidadAutonomaEntity> comunidadAutonomaJoin = root.join("escenario").join("proyecto").join("comunidadAutonoma");
            return comunidadAutonomaJoin.get("id").in(list);
        };
    }
   
	private static Specification<EjecucionEntity> byMunicipio(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, MunicipioEntity> municipioJoin = root.join("escenario").join("proyecto").join("municipio");
            return municipioJoin.get("id").in(list);
        };
    }
   
	private static Specification<EjecucionEntity> byProvincia(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, ProvinciaEntity> provinciaJoin = root.join("escenario").join("proyecto").join("provincia");
            return provinciaJoin.get("id").in(list);
        };
    }
	
    public static Specification<EjecucionEntity> byExecutionHour(LocalTime localTime) {
    	System.out.println(localTime.toString());
    	return (root, query, cb) -> cb.like((root.get("auditable").get("insertDate").as(String.class)), "%T" +   localTime.toString() + "%");
    }
	
    public static Specification<EjecucionEntity> byBajaDateIsNull() {
        return (root, query, cb) -> cb.isNull(root.get("bajaDate"));
    }
	   
   public static Specification<EjecucionEntity> buildSpecification(String name,
		   Integer estado, Long id,List<Integer> comunidades,List<Integer> municipios,List<Integer> provincias, 
		   Date startExecutionDate, Date endExecutionDate, String proyecto, String username, Long idProyecto, Long escenarioId, LocalTime executionHour) {
	   
		Specification<EjecucionEntity> specification = Specification.where(null);
		
		if (name != null) {
			specification = specification.and(byName(name));
		}
		if (estado != null) {
			specification = specification.and(byEstado(estado));
		}
		if (id != null) {
			specification = specification.and(byEscenario(id));
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
		if (startExecutionDate != null && endExecutionDate != null ) {
			specification = specification.and(byExecutionDate(startExecutionDate, endExecutionDate));
		}
		if (proyecto != null) {
			specification = specification.and(byProyecto(proyecto));
		}
		if (username != null) {
			specification = specification.and(byUser(username));
		}
		if (idProyecto != null) {
			specification = specification.and(byIdProyecto(idProyecto));
		}
		if (escenarioId != null) {
			specification = specification.and(byEscenario(escenarioId));
		}
		if (executionHour != null) {
			//specification = specification.and(byExecutionHour(executionHour));
		}
		specification.and(byBajaDateIsNull());
		
		return specification;
	}

}
