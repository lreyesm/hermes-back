package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.TipoVariacion;
import es.eroski.phermesback.model.TipoVariacionEntity;
@Mapper
public interface TipoVariacionMapper {

	
	@Named("toComplete")
	TipoVariacion toDto(TipoVariacionEntity entity);

	@Named("toMinimal")
	TipoVariacion toMinimalDto(TipoVariacionEntity entity);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	TipoVariacionEntity toEntity(TipoVariacion dto);


}
