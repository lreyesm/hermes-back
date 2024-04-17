package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.EstadoEjecucion;
import es.eroski.phermesback.model.EstadoEjecucionEntity;
@Mapper
public interface EstadoEjecucionMapper {
	
	
	@Named("toComplete")
	EstadoEjecucion toDto(EstadoEjecucionEntity entity);

	@Named("toMinimal")
	EstadoEjecucion toMinimalDto(EstadoEjecucionEntity entity);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	EstadoEjecucionEntity toEntity(EstadoEjecucion dto);

}
