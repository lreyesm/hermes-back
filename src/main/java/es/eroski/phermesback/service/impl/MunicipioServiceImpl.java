package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.Municipio;
import es.eroski.phermesback.mapper.MunicipioMapper;
import es.eroski.phermesback.model.MunicipioEntity;
import es.eroski.phermesback.model.ProvinciaEntity;
import es.eroski.phermesback.persistence.MunicipioRepository;
import es.eroski.phermesback.persistence.ProvinciaRepository;
import es.eroski.phermesback.service.MunicipioService;

@Service
public class MunicipioServiceImpl implements MunicipioService {

	@Autowired
	MunicipioRepository repository;

	@Autowired
	ProvinciaRepository provinciaRepository;

	MunicipioMapper mapper;

	public MunicipioServiceImpl(MunicipioMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Municipio> getMunicipioList() {
		List<MunicipioEntity> entities = repository.findAll();
		List<Municipio> dtoList = new ArrayList<Municipio>();
		for (MunicipioEntity entity : entities) {
			Municipio dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<Municipio> getMunicipioListByIdProvincia(List<Integer> id) {
		List<ProvinciaEntity> provincia = provinciaRepository.findAllById(id);
		List<MunicipioEntity> entities = repository.findByProvinciaIn(provincia);
		List<Municipio> dtoList = new ArrayList<Municipio>();
		for (MunicipioEntity entity : entities) {
			Municipio dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public Municipio getMunicipioById(Integer idProvincia, Integer idMunicipio) {
		ProvinciaEntity provincia = provinciaRepository.findById(idProvincia).get();
		MunicipioEntity entity = repository.findByIdAndProvincia(idMunicipio, provincia);
		return mapper.toDto(entity);
	}
}
