package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.EstadoProyecto;
import es.eroski.phermesback.mapper.EstadoProyectoMapper;
import es.eroski.phermesback.model.EstadoProyectoEntity;
import es.eroski.phermesback.persistence.EstadoProyectoRepository;
import es.eroski.phermesback.service.EstadoProyectoService;

@Service
public class EstadoProyectoServiceImpl implements EstadoProyectoService {

	@Autowired
	EstadoProyectoRepository repository;

	EstadoProyectoMapper mapper;

	public EstadoProyectoServiceImpl(EstadoProyectoMapper mapper) {
		this.mapper = mapper;
	}

	public List<EstadoProyecto> getEstadoProyectoList() {
		List<EstadoProyectoEntity> entities = repository.findAll();
		List<EstadoProyecto> dtoList = new ArrayList<EstadoProyecto>();
		for (EstadoProyectoEntity entity : entities) {
			EstadoProyecto dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public EstadoProyecto getEstadoProyectoById(Integer id) {
		EstadoProyectoEntity entity = repository.findById(id).get();
		return mapper.toDto(entity);
	}
}
