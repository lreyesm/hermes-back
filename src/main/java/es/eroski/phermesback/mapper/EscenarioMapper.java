package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import org.springframework.stereotype.Service;
import es.eroski.phermesback.dto.Escenario;
import es.eroski.phermesback.dto.request.EscenarioRequest;
import es.eroski.phermesback.model.EscenarioEntity;

/**
 * @clase ItemCategoryMapper
 * @brief Mapper para convertir entre ItemCategoryEntity y DTOs ItemCategory.
 */
@Mapper
public interface EscenarioMapper {

    @Named("toComplete")
    Escenario toDto(EscenarioEntity entity);


    @Named("toMinimal")
    @Mapping(target = "estado", ignore = true)
    Escenario toMinimalDto(EscenarioEntity entity);


    @Named("toComplete")
    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "isNew", ignore = true)
    EscenarioEntity toEntity(Escenario dto);
    
    @Named("toComplete")
    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "isNew", ignore = true)
    EscenarioEntity toEntity(EscenarioRequest dto);
}
