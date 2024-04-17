package es.eroski.phermesback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Provincia {
	private Integer id;
	private String nombre;
	private ComunidadAutonoma comunidadAutonoma;
}
