/**
 * @file
 * @brief Interfaz para el servicio de gestión de entidades de segmento de artículo.
 */
package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.Variacion;
import es.eroski.phermesback.dto.request.VariacionRequest;

public interface VariacionService{
	public Variacion getVariacion(Long variacionId);
	
	public Variacion createVariacion(VariacionRequest variacion);
	
	public PageCustom getVariacionesByEscenarioId(Long escenarioId, int page, int size);

	public void deleteVariacion(Long id);
}
