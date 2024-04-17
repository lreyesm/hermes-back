package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.TipoFormato;
import es.eroski.phermesback.model.TipoFormatoEntity;
@Mapper
public interface TipoFormatoMapper {
	
	
	@Named("toComplete")
	TipoFormato toDto(TipoFormatoEntity entity);

	@Named("toMinimal")
	TipoFormato toMinimalDto(TipoFormatoEntity entity);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	TipoFormatoEntity toEntity(TipoFormato dto);

}
