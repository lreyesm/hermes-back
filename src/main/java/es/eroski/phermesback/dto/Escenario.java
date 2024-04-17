package es.eroski.phermesback.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Escenario {
	private static final long serialVersionUID = -970788346196887878L;

	private Long id;

	private String name;

	private String descripcion;

	private Integer municipio;

	private EstadoEscenario estado;

	private String escenario_base;

	private Integer numEscenario;

	private Integer numVariaciones;

	private Integer numEjecuciones;

	private String insertUser;
	
	private Instant startDate;

	private Proyecto proyecto;
	
	private Instant lastExecutionDate;

}
