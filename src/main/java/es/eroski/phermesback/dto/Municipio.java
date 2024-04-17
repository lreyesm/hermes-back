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
public class Municipio {
	private Integer id;
	private Integer codigo;
	private String nombre;
	private Provincia provincia;

}
