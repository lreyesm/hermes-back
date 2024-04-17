package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.EstadoCentro;
import es.eroski.phermesback.model.EstadoCentroEntity;
@Mapper
public interface EstadoCentroMapper {
	
	@Named("toComplete")
	EstadoCentro toDto(EstadoCentroEntity entity);

	@Named("toMinimal")
	EstadoCentro toMinimalDto(EstadoCentroEntity entity);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	EstadoCentroEntity toEntity(EstadoCentro dto);
}
