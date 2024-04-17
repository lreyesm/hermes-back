package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.EstadoEjecucion;
import es.eroski.phermesback.mapper.EstadoEjecucionMapper;
import es.eroski.phermesback.model.EstadoEjecucionEntity;
import es.eroski.phermesback.persistence.EstadoEjecucionRepository;
import es.eroski.phermesback.service.EstadoEjecucionService;

@Service
public class EstadoEjecucionServiceImpl implements EstadoEjecucionService {

	@Autowired
	EstadoEjecucionRepository repository;

	EstadoEjecucionMapper mapper;

	public EstadoEjecucionServiceImpl(EstadoEjecucionMapper mapper) {
		this.mapper = mapper;
	}

	public List<EstadoEjecucion> getEstadoEjecucionList() {
		List<EstadoEjecucionEntity> entities = repository.findAll();
		List<EstadoEjecucion> dtoList = new ArrayList<EstadoEjecucion>();
		for (EstadoEjecucionEntity entity : entities) {
			EstadoEjecucion dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public EstadoEjecucion getEstadoEjecucionById(Integer id) {
		EstadoEjecucionEntity entity = repository.findById(id).get();
		return mapper.toDto(entity);
	}

}
