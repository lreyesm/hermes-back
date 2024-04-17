package es.eroski.phermesback.persistence.specifications;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import es.eroski.phermesback.dto.Proyecto;
import es.eroski.phermesback.model.ComunidadAutonomaEntity;
import es.eroski.phermesback.model.MunicipioEntity;
import es.eroski.phermesback.model.ProvinciaEntity;
import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.EscenarioEntity;
import jakarta.persistence.criteria.Join;

public class EscenarioEspecifications {
	
	private static Specification<EscenarioEntity> byProyecto(Long id) {
        return (root, query, cb) -> cb.equal(root.get("proyecto").get("id"), id);
    }
	
	private static Specification<EscenarioEntity> byProyectoAsociado(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("proyecto").get("name")), "%" +   name.toLowerCase() + "%");
    }
	
	private static Specification<EscenarioEntity> byBase(Integer base) {
        return (root, query, cb) -> cb.equal(root.get("escenario_base"), base);
    }
	
	private static Specification<EscenarioEntity> byEstado(Integer estado) {
        return (root, query, cb) -> cb.equal(root.get("estado").get("id"), estado);
    }
   
	private static Specification<EscenarioEntity> byName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" +   name.toLowerCase() + "%");
    }
   
	private static Specification<EscenarioEntity> byId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
	
	private static Specification<EscenarioEntity> byCreationDate(Date startDate, Date endDate) {
        return (root, query, cb) -> cb.between(root.get("auditable").get("insertDate"), startDate, endDate );
    }
	
	private static Specification<EscenarioEntity> byExecutionDate(Date startDate, Date endDate) {
        return (root, query, cb) -> cb.between(root.get("auditable").get("insertDate"), startDate, endDate );
    }
   
	private static Specification<EscenarioEntity> byComunidadAutonoma(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, ComunidadAutonomaEntity> comunidadAutonomaJoin = root.join("proyecto").join("comunidadAutonoma");
            return comunidadAutonomaJoin.get("id").in(list);
        };
    }
   
	private static Specification<EscenarioEntity> byMunicipio(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, MunicipioEntity> municipioJoin = root.join("proyecto").join("municipio");
            return municipioJoin.get("id").in(list);
        };
    }
   
	private static Specification<EscenarioEntity> byProvincia(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, ProvinciaEntity> provinciaJoin = root.join("proyecto").join("provincia");
            return provinciaJoin.get("id").in(list);
        };
    }
	
    public static Specification<EscenarioEntity> byBajaDateIsNull() {
        return (root, query, cb) -> cb.isNull(root.get("bajaDate"));
    }
	   
   public static Specification<EscenarioEntity> buildSpecification(String name,
		   Integer estado, Long id, Integer base, String proyectoAsociadoName, List<Integer> comunidades,List<Integer> municipios,List<Integer> provincias, 
		   Date startCreationDate, Date endCreationDate, Long proyectoId) {
	   
		Specification<EscenarioEntity> specification = Specification.where(null);
		
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
		
		if (proyectoId != null) {
		specification = specification.and(byProyecto(proyectoId));
		}
		if (base != null) {
		specification = specification.and(byBase(base));
		}
		if (proyectoAsociadoName != null) {
		specification = specification.and(byProyectoAsociado(proyectoAsociadoName));
		}
		
		specification.and(byBajaDateIsNull());
		
		return specification;
	}

}
