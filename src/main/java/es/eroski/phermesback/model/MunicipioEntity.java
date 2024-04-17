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
@Entity(name = "municipio")
public class MunicipioEntity implements Persistable<Integer>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4698279849436832340L;
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "cod_municipio")
	private Integer codigo;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_provincia", referencedColumnName = "id")
	private ProvinciaEntity provincia;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		MunicipioEntity that = (MunicipioEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Transient
	private boolean isNew;

}
