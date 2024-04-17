package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.Provincia;
import es.eroski.phermesback.mapper.ProvinciaMapper;
import es.eroski.phermesback.model.ComunidadAutonomaEntity;
import es.eroski.phermesback.model.ProvinciaEntity;
import es.eroski.phermesback.persistence.ComunidadAutonomaRepository;
import es.eroski.phermesback.persistence.ProvinciaRepository;
import es.eroski.phermesback.service.ProvinciaService;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {
	@Autowired
	ProvinciaRepository repository;

	@Autowired
	ComunidadAutonomaRepository cARepository;

	ProvinciaMapper mapper;

	public ProvinciaServiceImpl(ProvinciaMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Provincia> getProvinciaList() {
		List<ProvinciaEntity> entities = repository.findAll();
		List<Provincia> dtoList = new ArrayList<Provincia>();
		for (ProvinciaEntity entity : entities) {
			Provincia dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<Provincia> getProvinciaListByIdComunidadAutonoma(List<Integer> comunidades) {
		List<ComunidadAutonomaEntity> cA = cARepository.findAllById(comunidades);
		List<ProvinciaEntity> entities = repository.findByComunidadAutonomaIn(cA);
		List<Provincia> dtoList = new ArrayList<Provincia>();
		for (ProvinciaEntity entity : entities) {
			Provincia dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public Provincia getProvinciaById(Integer id) {
		ProvinciaEntity entity = repository.findById(id).get();
		return mapper.toDto(entity);
	}

}
