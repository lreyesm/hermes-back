package es.eroski.phermesback.model;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
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
@Entity(name = "estado_escenario")
public class EstadoEscenarioEntity implements Persistable<Integer>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4046608473257391839L;
	@Id
	@Column(name = "id")
	private Integer id;
	@NotNull
	@Column(name = "estado_escenario")
	private String estadoEscenario;
	

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		EstadoEscenarioEntity that = (EstadoEscenarioEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	

	@Transient
	private boolean isNew;
	
	
}
