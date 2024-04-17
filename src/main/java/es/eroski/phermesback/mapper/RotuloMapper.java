package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.Rotulo;
import es.eroski.phermesback.model.RotuloEntity;
@Mapper
public interface RotuloMapper {

	
	@Named("toComplete")
	Rotulo toDto(RotuloEntity entity);

	@Named("toMinimal")
	Rotulo toMinimalDto(RotuloEntity entity);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	RotuloEntity toEntity(Rotulo dto);
	
	

}
