package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.Escenario;
import es.eroski.phermesback.dto.Variacion;
import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.VariacionEntity;

/**
 * @clase ItemCategoryMapper
 * @brief Mapper para convertir entre ItemCategoryEntity y DTOs ItemCategory.
 */
@Mapper
public interface VariacionMapper {

    @Named("toComplete")
    Variacion toDto(VariacionEntity entity);


    @Named("toMinimal")
    Variacion toMinimalDto(VariacionEntity entity);


    @Named("toComplete")
    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "isNew", ignore = true)
    VariacionEntity toEntity(Variacion dto);
}
