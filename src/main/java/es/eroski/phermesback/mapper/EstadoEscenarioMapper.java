package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.EstadoEscenario;
import es.eroski.phermesback.model.EstadoEscenarioEntity;
@Mapper
public interface EstadoEscenarioMapper {
	

	
	@Named("toComplete")
	   EstadoEscenario toDto(EstadoEscenarioEntity entity);


	    @Named("toMinimal")
	   EstadoEscenario toMinimalDto(EstadoEscenarioEntity entity);


	    @Named("toComplete")
	    @InheritInverseConfiguration(name = "toDto")
	    @Mapping(target = "isNew", ignore = true)
	   EstadoEscenarioEntity toEntity(EstadoEscenario dto);

}
