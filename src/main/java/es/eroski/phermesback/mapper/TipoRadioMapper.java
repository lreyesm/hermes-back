package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.TipoRadio;
import es.eroski.phermesback.model.TipoRadioEntity;
@Mapper
public interface TipoRadioMapper {

	@Named("toComplete")
	TipoRadio toDto(TipoRadioEntity entity);

	@Named("toMinimal")
	TipoRadio toMinimalDto(TipoRadioEntity entity);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	TipoRadioEntity toEntity(TipoRadio dto);

}
