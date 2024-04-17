package es.eroski.phermesback.model;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "provincia")
public class ProvinciaEntity implements Persistable<Integer>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1004350278102521175L;
	@Id
	@Column(name = "id")
	private Integer id;
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_com_auto", referencedColumnName = "id")
	private ComunidadAutonomaEntity comunidadAutonoma;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		ProvinciaEntity that = (ProvinciaEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Transient
	private boolean isNew;

}
