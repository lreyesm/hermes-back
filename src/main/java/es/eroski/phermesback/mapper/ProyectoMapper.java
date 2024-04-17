package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.Proyecto;
import es.eroski.phermesback.dto.request.ProyectoRequest;
import es.eroski.phermesback.model.ProyectoEntity;

/**
 * @clase ItemCategoryMapper
 * @brief Mapper para convertir entre ItemCategoryEntity y DTOs ItemCategory.
 */
@Mapper
public interface ProyectoMapper {

    @Named("toComplete")
    Proyecto toDto(ProyectoEntity entity);


    @Named("toMinimal")
    Proyecto toMinimalDto(ProyectoEntity entity);


    @Named("toComplete")
    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "isNew", ignore = true)
    ProyectoEntity toEntity(Proyecto dto);
    
    @Named("toComplete")
    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "radioType", ignore = true)
    @Mapping(target = "isNew", ignore = true)
	@Mapping(target = "provincia", ignore = true)
	@Mapping(target = "comunidadAutonoma", ignore = true)
	@Mapping(target = "municipio", ignore = true)
    ProyectoEntity toEntity(ProyectoRequest dto);
}
