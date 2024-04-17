package es.eroski.phermesback.resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.Instant;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


import es.eroski.phermesback.controller.CentroController;
import es.eroski.phermesback.dto.CentroFicticio;
import es.eroski.phermesback.dto.EstadoCentro;
import es.eroski.phermesback.dto.Rotulo;
import es.eroski.phermesback.dto.TipoFormato;
import es.eroski.phermesback.dto.TipoParking;
import es.eroski.phermesback.dto.TipoVariacion;
import es.eroski.phermesback.dto.request.CentroFicticioRequest;
import es.eroski.phermesback.mapper.CentroFicticioMapper;
import es.eroski.phermesback.model.CentroFicticioEntity;
import es.eroski.phermesback.model.EstadoCentroEntity;
import es.eroski.phermesback.model.RotuloEntity;
import es.eroski.phermesback.model.TipoFormatoEntity;
import es.eroski.phermesback.model.TipoParkingEntity;
import es.eroski.phermesback.model.TipoVariacionEntity;
import es.eroski.phermesback.model.embedded.AuditableEntity;
import es.eroski.phermesback.persistence.CentroFicticioRepository;
import es.eroski.phermesback.service.impl.CentroServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CentroControllerTest {
    
    private static final Logger logger = LogManager.getLogger(CentroControllerTest.class);

    @Autowired
    private CentroController centroController; 
    
    
    @Test
    public void TestCentroFicticio() {
        logger.info("TEST: testAltaCentro");
        Authentication authentication = new UsernamePasswordAuthenticationToken("i2928", null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		CentroFicticioRequest cfr = new CentroFicticioRequest();
		
		cfr.setName("TestCentroFicticio");
		cfr.setRotulo(1);
		cfr.setTipoFormato(10);
		cfr.setAccesoParking(0);
		cfr.setComunidadAutonoma(1);
		cfr.setDireccion("");
		cfr.setLatitud(10.454f);
		cfr.setLongitud(10.454f);
		cfr.setMotivoCreacion(1);

		ResponseEntity<CentroFicticio> cf = centroController.createCentroFicticio(cfr);
		
		assertNotNull(cf);
    }
    
      
}
