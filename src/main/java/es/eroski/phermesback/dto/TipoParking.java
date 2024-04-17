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
public class TipoParking implements Serializable {

	private static final long serialVersionUID = -3858135120331316371L;
	private Integer id;
	private String tipoParking;

}
