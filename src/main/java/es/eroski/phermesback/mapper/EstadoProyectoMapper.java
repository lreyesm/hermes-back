package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.EstadoProyecto;
import es.eroski.phermesback.model.EstadoProyectoEntity;
@Mapper
public interface EstadoProyectoMapper{
	
	@Named("toComplete")
	EstadoProyecto toDto(EstadoProyectoEntity entity);

	@Named("toMinimal")
	EstadoProyecto toMinimalDto(EstadoProyectoEntity entity);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	EstadoProyectoEntity toEntity(EstadoProyecto dto);
}
