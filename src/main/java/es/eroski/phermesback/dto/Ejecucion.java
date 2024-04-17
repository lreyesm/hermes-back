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
public class Ejecucion {

	private static final long serialVersionUID = 679911016151696006L;

	private Long id;

	private String type;

	private EstadoEjecucion estado;

	private String resultado;

	private Instant fecha_resultado;

	private String insertUser;

	private Instant insertDate;

	private Instant bajaDate;

	private Escenario escenario;
	
	private Proyecto proyecto;
	
	private Integer numEjecuciones;
	
}
