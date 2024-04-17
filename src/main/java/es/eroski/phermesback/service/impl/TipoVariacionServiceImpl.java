package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.TipoVariacion;
import es.eroski.phermesback.mapper.TipoVariacionMapper;
import es.eroski.phermesback.model.TipoVariacionEntity;
import es.eroski.phermesback.persistence.TipoVariacionRepository;
import es.eroski.phermesback.service.TipoVariacionService;

@Service
public class TipoVariacionServiceImpl implements TipoVariacionService {

	@Autowired
	TipoVariacionRepository repository;

	TipoVariacionMapper mapper;

	public TipoVariacionServiceImpl(TipoVariacionMapper mapper) {
		this.mapper = mapper;
	}

	public List<TipoVariacion> getTipoVariacionList() {
		List<TipoVariacionEntity> entities = repository.findAll();
		List<TipoVariacion> dtoList = new ArrayList<TipoVariacion>();
		for (TipoVariacionEntity entity : entities) {
			TipoVariacion dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public TipoVariacion getTipoVariacionById(Integer id) {
		TipoVariacionEntity entity = repository.findById(id).get();
		return mapper.toDto(entity);
	}

}
