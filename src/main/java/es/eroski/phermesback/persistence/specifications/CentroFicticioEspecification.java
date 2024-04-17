package es.eroski.phermesback.persistence.specifications;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import es.eroski.phermesback.dto.Proyecto;
import es.eroski.phermesback.model.ComunidadAutonomaEntity;
import es.eroski.phermesback.model.MunicipioEntity;
import es.eroski.phermesback.model.ProvinciaEntity;
import es.eroski.phermesback.model.CentroFicticioEntity;
import jakarta.persistence.criteria.Join;

public class CentroFicticioEspecification {

	private static Specification<CentroFicticioEntity> byEstado(Integer estado) {
        return (root, query, cb) -> cb.equal(root.get("estado").get("id"), estado);
    }
   
	private static Specification<CentroFicticioEntity> byName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" +   name.toLowerCase() + "%");
    }
	
	private static Specification<CentroFicticioEntity> byDireccion(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("direccion")), "%" +   name.toLowerCase() + "%");
    }
	
	private static Specification<CentroFicticioEntity> byMetrosCuadrados(Float value) {
        return (root, query, cb) -> cb.equal(root.get("metrosCuadrados"), value);
    }
	 
	private static Specification<CentroFicticioEntity> byAccesoParking(Integer value) {
        return (root, query, cb) -> cb.equal(root.get("accesoParking").get("id"), value);
    }
	 
   
	private static Specification<CentroFicticioEntity> byMotivoCreacion(Integer id) {
        return (root, query, cb) -> cb.equal(root.get("motivoCreacion").get("id"), id);
    }
	
	private static Specification<CentroFicticioEntity> byIdOrigen(Long id) {
        return (root, query, cb) -> cb.equal(root.get("centroEscenario").get("id"), id);
    }
	
	private static Specification<CentroFicticioEntity> byId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
	
	private static Specification<CentroFicticioEntity> byCreationDate(Date startDate, Date endDate) {
        return (root, query, cb) -> cb.between(root.get("auditable").get("insertDate"), startDate, endDate );
    }
   
	private static Specification<CentroFicticioEntity> byComunidadAutonoma(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, ComunidadAutonomaEntity> comunidadAutonomaJoin = root.join("comunidadAutonoma");
            return comunidadAutonomaJoin.get("id").in(list);
        };
    }
   
	private static Specification<CentroFicticioEntity> byMunicipio(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, MunicipioEntity> municipioJoin = root.join("municipio");
            return municipioJoin.get("id").in(list);
        };
    }
   
	private static Specification<CentroFicticioEntity> byProvincia(List<Integer> list) {
       return (root, query, criteriaBuilder) -> {
            Join<Proyecto, ProvinciaEntity> provinciaJoin = root.join("provincia");
            return provinciaJoin.get("id").in(list);
        };
    }
	
    public static Specification<CentroFicticioEntity> byBajaDateIsNull() {
        return (root, query, cb) -> cb.isNull(root.get("bajaDate"));
    }
    
	private static Specification<CentroFicticioEntity> byTipoFormato(Integer id) {
        return (root, query, cb) -> cb.equal(root.get("tipoFormato").get("id"), id);
    }
	
	private static Specification<CentroFicticioEntity> byRotulo(Integer id) {
        return (root, query, cb) -> cb.equal(root.get("rotulo").get("id"), id);
    }
	   
   public static Specification<CentroFicticioEntity> buildSpecification(String name,
		   Integer estado, Long id,List<Integer> comunidades,List<Integer> municipios,List<Integer> provincias, 
		   Date startCreationDate, Date endCreationDate, Long idOrigen, Integer motivoCreacion, Float metrosCuadrados, Integer tipoParking, String direccion, Integer tipoFormato, Integer rotulo) {
	   
		Specification<CentroFicticioEntity> specification = Specification.where(null);
		
		if (name != null) {
			specification = specification.and(byName(name));
		}
		
		if (estado != null) {
			specification = specification.and(byEstado(estado));
		}
		
		if (idOrigen != null) {
			specification = specification.and(byIdOrigen(idOrigen));
		}
		
		if (motivoCreacion != null) {
			specification = specification.and(byMotivoCreacion(motivoCreacion));
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
		
		if (metrosCuadrados != null) {
			specification = specification.and(byMetrosCuadrados(metrosCuadrados));
		}
		
		if (direccion != null) {
			specification = specification.and(byDireccion(direccion));
		}
		
		if (tipoParking != null) {
			specification = specification.and(byAccesoParking(tipoParking));
		}
		
		if (tipoFormato != null) {
			specification = specification.and(byTipoFormato(tipoFormato));
		}
		
		if (rotulo != null) {
			specification = specification.and(byRotulo(rotulo));
		}
		
		//specification.and(byBajaDateIsNull());
		
		return specification;
	}

}
