package es.eroski.phermesback.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Proyecto implements Serializable {
	private static final long serialVersionUID = -8472167421304063261L;

	private Long id;

	private String name;

	private String type;

	private Float latitud;

	private Float longitud;

	private Float radioIncidencia;

	private TipoRadio radioType;

	private Float time;

	private Instant startDate;

	private ComunidadAutonoma comunidadAutonoma;

	private Provincia provincia;

	private Municipio municipio;

	private String descripcion;

	private EstadoProyecto estado;

	private Integer numeroEscenarios;

	private String insertUser;


	//private List<Escenario> escenarios;

}
