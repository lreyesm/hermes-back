package es.eroski.phermesback.dto.request;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProyectoRequest implements Serializable {
	private static final long serialVersionUID = -8472167421304063261L;
	
	//Only for update
	private Long id;
	
	private String name;

	private String type;

	private Float latitud;

	private Float longitud;

	private Float radioIncidencia;

	private Integer radioType;

	private Float time;

	private Integer comunidadAutonoma;

	private Integer provincia;

	private Integer municipio;

	private String descripcion;


}
