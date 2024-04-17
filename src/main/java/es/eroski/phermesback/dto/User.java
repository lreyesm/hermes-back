/**
 * @file User.java
 * @brief Clase que representa un usuario en el sistema.
 */
package es.eroski.phermesback.dto;

import java.io.Serializable;
import java.util.Set;

import org.springframework.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
/**
 * @brief Clase que representa un usuario en el sistema.
 *
 * Esta clase representa un usuario en el sistema y contiene información como el nombre de usuario,
 * el nombre, el apellido, el correo electrónico y los roles del usuario.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    /**
     * @brief Nombre de usuario del usuario.
     */
    @NotNull
    @Size(max = 255)
    private String username;
    
    /**
     * @brief Nombre del usuario.
     */
    @Size(max = 255)
    @Nullable
    private String name;

    /**
     * @brief Apellido del usuario.
     */
    @Size(max = 255)
    @Nullable
    private String lastName;

    /**
     * @brief Correo electrónico del usuario.
     */
    @Size(max = 255)
    @Nullable
    private String email;

}
