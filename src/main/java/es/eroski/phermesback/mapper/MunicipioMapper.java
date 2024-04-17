package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.Municipio;
import es.eroski.phermesback.model.MunicipioEntity;
@Mapper
public interface MunicipioMapper {


	
	@Named("toComplete")
	   Municipio toDto(MunicipioEntity entity);


	    @Named("toMinimal")
	   Municipio toMinimalDto(MunicipioEntity entity);


	    @Named("toComplete")
	    @InheritInverseConfiguration(name = "toDto")
	    @Mapping(target = "isNew", ignore = true)
	   MunicipioEntity toEntity(Municipio dto);
}
