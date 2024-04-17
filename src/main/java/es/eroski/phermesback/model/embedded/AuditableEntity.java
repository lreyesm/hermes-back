/**
 * @archivo
 * Clase que representa una entidad auditada, contiene información sobre quién creó y modificó
 * el objeto y cuándo se realizó dicha acción.
 */
package es.eroski.phermesback.model.embedded;

import java.io.Serializable;
import java.time.Instant;

import es.eroski.oinarri.library.ldaplogin.lib.user.domain.User;
import es.eroski.phermesback.model.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @clase AuditableEntity
 * @brief Clase que representa una entidad auditada, contiene información sobre quién creó y modificó
 * el objeto y cuándo se realizó dicha acción.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class AuditableEntity implements Serializable {

    /**
     * @brief Constructor que toma un usuario y lo establece como usuario de inserción y modificación.
     * @param user El usuario que realizará la inserción y modificación.
     */
    public AuditableEntity(User user) {
      this.insertUser = user;
      this.updateUser = user;
    }

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_insertado", referencedColumnName = "id", updatable = false)
    private User insertUser;

    @Column(name = "fecha_insertado", updatable = false)
    private Instant insertDate = Instant.now();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_modificado", referencedColumnName = "id")
    private User updateUser;

    @Column(name = "fecha_modificado")
    private Instant updateDate = Instant.now();

    /**
     * @brief Método para actualizar el usuario de modificación y la fecha de modificación.
     * @param user El nuevo usuario que realizará la modificación.
     * @return La instancia de AuditableEntity actualizada.
     */
    public AuditableEntity changeBy(User user) {
      this.updateUser = user;
      this.updateDate = Instant.now();

      return this;
    }
}
