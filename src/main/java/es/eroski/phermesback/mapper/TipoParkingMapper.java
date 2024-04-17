package es.eroski.phermesback.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.eroski.phermesback.dto.TipoParking;
import es.eroski.phermesback.model.TipoParkingEntity;

@Mapper
public interface TipoParkingMapper {
	@Named("toComplete")
	TipoParking toDto(TipoParkingEntity entity);

	@Named("toMinimal")
	TipoParking toMinimalDto(TipoParkingEntity entity);

	@Named("toComplete")
	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "isNew", ignore = true)
	TipoParkingEntity toEntity(TipoParking dto);

}
