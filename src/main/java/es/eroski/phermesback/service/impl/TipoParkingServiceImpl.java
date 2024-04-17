package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.TipoParking;
import es.eroski.phermesback.mapper.TipoParkingMapper;
import es.eroski.phermesback.model.TipoParkingEntity;
import es.eroski.phermesback.persistence.TipoParkingRepository;
import es.eroski.phermesback.service.TipoParkingService;

@Service
public class TipoParkingServiceImpl implements TipoParkingService {
	@Autowired
	TipoParkingRepository repository;

	TipoParkingMapper mapper;

	public TipoParkingServiceImpl(TipoParkingMapper mapper) {
		this.mapper = mapper;
	}

	public List<TipoParking> getTipoParkingList() {
		List<TipoParkingEntity> entities = repository.findAll();
		List<TipoParking> dtoList = new ArrayList<TipoParking>();
		for (TipoParkingEntity entity : entities) {
			TipoParking dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public TipoParking getTipoParkingById(Integer id) {
		TipoParkingEntity entity = repository.findById(id).get();
		return mapper.toDto(entity);
	}

}
