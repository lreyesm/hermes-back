package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.ComunidadAutonoma;
import es.eroski.phermesback.mapper.ComunidadAutonomaMapper;
import es.eroski.phermesback.model.ComunidadAutonomaEntity;
import es.eroski.phermesback.persistence.ComunidadAutonomaRepository;
import es.eroski.phermesback.service.ComunidadAutonomaService;
@Service
public class ComunidadAutonomaServiceImpl implements ComunidadAutonomaService{
	@Autowired
	private ComunidadAutonomaRepository comunidadAutonomaRepository;

	private final ComunidadAutonomaMapper comunidadAutonomaMapper;

	public ComunidadAutonomaServiceImpl(ComunidadAutonomaMapper comunidadAutonomaMapper) {
		this.comunidadAutonomaMapper = comunidadAutonomaMapper;
	}
	public List<ComunidadAutonoma> getComunidadAutonomaList() {
		List<ComunidadAutonomaEntity> entities = comunidadAutonomaRepository.findAll();	
		List<ComunidadAutonoma> cAList = new ArrayList<ComunidadAutonoma>();
		for(ComunidadAutonomaEntity entity: entities) {
			ComunidadAutonoma cA = comunidadAutonomaMapper.toDto(entity);
			cAList.add(cA);
		}
		return cAList;
		
	}
	public ComunidadAutonoma getComunidadAutonomaById(Integer id) {
		ComunidadAutonomaEntity entity = comunidadAutonomaRepository.findById(id).get();
		return  comunidadAutonomaMapper.toDto(entity);
		
	}
}
