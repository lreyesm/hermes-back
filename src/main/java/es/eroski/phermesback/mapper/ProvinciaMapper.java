package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.Provincia;
import es.eroski.phermesback.model.ProvinciaEntity;
@Mapper
public interface ProvinciaMapper {

	@Named("toComplete")
	Provincia toDto(ProvinciaEntity entity);

	@Named("toMinimal")
	Provincia toMinimalDto(ProvinciaEntity entity);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	ProvinciaEntity toEntity(Provincia dto);
}
