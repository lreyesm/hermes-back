package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.CentroEscenario;
import es.eroski.phermesback.dto.Ejecucion;
import es.eroski.phermesback.model.EjecucionEntity;

/**
 * @clase ItemCategoryMapper
 * @brief Mapper para convertir entre ItemCategoryEntity y DTOs ItemCategory.
 */
@Mapper
public interface EjecucionMapper {

    @Named("toComplete")
    @Mapping(target = "escenario", ignore = true)
    Ejecucion toDto(EjecucionEntity entity);


    @Named("toMinimal")
    @Mapping(target = "escenario", ignore = true)
    Ejecucion toMinimalDto(EjecucionEntity entity);


    @Named("toComplete")
    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "isNew", ignore = true)
    @Mapping(target = "escenario", ignore = true)
    EjecucionEntity toEntity(Ejecucion dto);
}
