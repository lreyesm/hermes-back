package es.eroski.phermesback.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.eroski.phermesback.dto.HomeData;
import es.eroski.phermesback.service.HomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;


@RestController
@RequestMapping("home")
@Tag(name = "HomeController", description = "Operaciones del controlador de home")
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	@Autowired
	private HomeService homeService;
	
	/**
	 * @brief Constructor de la clase CentroController.
	 */
	public HomeController() {
	}

	/**
	 * @brief Obtiene los detalles de la pantalla de inicio
	 *
	 * @return detalles de la pantalla de inicio
	 */
	@GetMapping("")
	public ResponseEntity<HomeData> getCentroFicticio() {
		logger.info("Getting home data");
		try {
			HomeData data = homeService.getHomeData();
			return new ResponseEntity<HomeData>(data, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.error("Error getting home data: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			logger.error("Error home data: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
