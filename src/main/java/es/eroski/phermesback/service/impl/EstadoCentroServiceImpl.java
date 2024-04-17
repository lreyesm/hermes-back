package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.EstadoCentro;
import es.eroski.phermesback.mapper.EstadoCentroMapper;
import es.eroski.phermesback.model.EstadoCentroEntity;
import es.eroski.phermesback.persistence.EstadoCentroRepository;
import es.eroski.phermesback.service.EstadoCentroService;

@Service
public class EstadoCentroServiceImpl implements EstadoCentroService {

	@Autowired
	EstadoCentroRepository repository;

	EstadoCentroMapper mapper;

	public EstadoCentroServiceImpl(EstadoCentroMapper mapper) {
		this.mapper = mapper;
	}

	public List<EstadoCentro> getEstadosCentroList() {
		List<EstadoCentroEntity> entities = repository.findAll();
		List<EstadoCentro> dtoList = new ArrayList<EstadoCentro>();
		for (EstadoCentroEntity entity : entities) {
			EstadoCentro dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public EstadoCentro getEstadoCentroById(Integer id) {

		EstadoCentroEntity entity = repository.findById(id).get();
		return mapper.toDto(entity);
	}

}
