package es.eroski.phermesback.dto;

import java.io.Serializable;

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
public class HomeData implements Serializable {

	private static final long serialVersionUID = 4513195728943349717L;

	private Long countProyectos;
	
	private Long countEscenarios;
	
	private Long countEjecuciones;
	
	private Long countCentros;

}
