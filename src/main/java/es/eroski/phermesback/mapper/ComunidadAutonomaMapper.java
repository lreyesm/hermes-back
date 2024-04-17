package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.ComunidadAutonoma;
import es.eroski.phermesback.model.ComunidadAutonomaEntity;

@Mapper
public interface ComunidadAutonomaMapper {

	@Named("toComplete")
	ComunidadAutonoma toDto(ComunidadAutonomaEntity entity);

	@Named("toMinimal")
	ComunidadAutonoma toMinimalDto(ComunidadAutonomaEntity entity);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	ComunidadAutonomaEntity toEntity(ComunidadAutonoma dto);
}
