package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.CentroFicticio;
import es.eroski.phermesback.model.CentroFicticioEntity;
import es.eroski.phermesback.dto.request.CentroFicticioRequest;

/**
 * @clase ItemCategoryMapper
 * @brief Mapper para convertir entre ItemCategoryEntity y DTOs ItemCategory.
 */
@Mapper
public interface CentroFicticioMapper {

	@Named("toComplete")
	CentroFicticio toDto(CentroFicticioEntity entity);

	@Named("toMinimal")
	CentroFicticio toMinimalDto(CentroFicticioEntity entity);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	CentroFicticioEntity toEntity(CentroFicticio dto);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	@Mapping(target = "estado", ignore = true)
	@Mapping(target = "rotulo", ignore = true)
	@Mapping(target = "tipoFormato", ignore = true)
	@Mapping(target = "accesoParking", ignore = true)
	@Mapping(target = "motivoCreacion", ignore = true)
	@Mapping(target = "centroEscenario", ignore = true)
	@Mapping(target = "provincia", ignore = true)
	@Mapping(target = "comunidadAutonoma", ignore = true)
	@Mapping(target = "municipio", ignore = true)
	CentroFicticioEntity toEntity(CentroFicticioRequest centro);
}
