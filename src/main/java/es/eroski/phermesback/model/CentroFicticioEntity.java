package es.eroski.phermesback.model;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import es.eroski.phermesback.model.embedded.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
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
@Entity(name = "centro_ficticio")
public class CentroFicticioEntity implements Persistable<Long>, Serializable {

	private static final long serialVersionUID = 4643885420952394087L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigo_seq")
    @Column(name = "codigo", insertable = false, updatable = false)
    private Long codigo;

	@Column(name = "nombre")
	@Nullable
	private String name;

	@Column(name = "latitud")
	@Nullable
	private Float latitud;

	@Column(name = "longitud")
	@Nullable
	private Float longitud;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comunidad_autonoma", referencedColumnName = "id")
	@Nullable
	private ComunidadAutonomaEntity comunidadAutonoma;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provincia", referencedColumnName = "id")
	@Nullable
	private ProvinciaEntity provincia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "municipio", referencedColumnName = "id")
	@Nullable
	private MunicipioEntity municipio;

	@Column(name = "direccion")
	@Nullable
	private String direccion;

	@Column(name = "metros_cuadrados")
	@Nullable
	private Float metrosCuadrados;
	
	@Column(name = "localizacion")
	private Integer localizacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acceso_parking", referencedColumnName = "id")
	@Nullable
	private TipoParkingEntity accesoParking;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_formato", referencedColumnName = "id")
	@Nullable
	private TipoFormatoEntity tipoFormato;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rotulo", referencedColumnName = "id")
	@Nullable
	private RotuloEntity rotulo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado", referencedColumnName = "id")
	@Nullable
	private EstadoCentroEntity estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "motivo_creacion", referencedColumnName = "id")
	@Nullable
	private TipoVariacionEntity motivoCreacion;
	
	@Column(name = "centro_escenario")
	@Nullable
	private String centroEscenario;

	@Embedded
	private AuditableEntity auditable;

	@Transient
	private boolean isNew;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		CentroFicticioEntity that = (CentroFicticioEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
