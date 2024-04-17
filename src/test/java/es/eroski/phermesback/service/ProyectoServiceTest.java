package es.eroski.phermesback.service;

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
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

import es.eroski.phermesback.dto.CentroFicticio;
import es.eroski.phermesback.dto.EstadoCentro;
import es.eroski.phermesback.dto.Proyecto;
import es.eroski.phermesback.dto.Rotulo;
import es.eroski.phermesback.dto.TipoFormato;
import es.eroski.phermesback.dto.TipoParking;
import es.eroski.phermesback.dto.TipoVariacion;
import es.eroski.phermesback.dto.request.ProyectoRequest;
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
import es.eroski.phermesback.service.impl.ProyectoServiceImpl;

//@SpringBootTest
//@Transactional
@SuppressWarnings("unused")
@DataJpaTest
@ContextConfiguration(classes=PhermesTestConfig.class)
@EnableJpaRepositories(basePackages = {"es.eroski.ldaplogin.lib.user.domain","es.eroski.ldaplogin.lib.role.domain", 
		"es.eroski.ldaplogin.lib.permission.domain", "es.eroski.ldaplogin.lib.session.domain", "es.eroski.ldaplogin.lib.refreshtoken.domain"
		})
@EntityScan({"es.eroski.ldaplogin.lib.user.domain","es.eroski.ldaplogin.lib.role.domain", 
	"es.eroski.ldaplogin.lib.permission.domain", "es.eroski.ldaplogin.lib.refreshtoken.domain",  "es.eroski.ldaplogin.lib.session.domain"})
public class ProyectoServiceTest {
    
    private static final Logger logger = LogManager.getLogger(ProyectoServiceTest.class);

    @InjectMocks
    private ProyectoServiceImpl proyectoService; 
    
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
        
   /* @Test
    public void testCentroFicticioGet() {
    	 Proyecto proyecto = proyectoService.createProyecto(this.createDummy());
    	
    	assertNotNull(proyecto);
    }*/
    
    private ProyectoRequest createDummy() {
        ProyectoRequest proyectoRequest = new ProyectoRequest();
        proyectoRequest.setId(1L);
        proyectoRequest.setName("Dummy Name");
        proyectoRequest.setType("Dummy Type");
        proyectoRequest.setLatitud(40.7128f);
        proyectoRequest.setLongitud(-74.0060f);
        proyectoRequest.setRadioIncidencia(10.5f);
        proyectoRequest.setRadioType(1);
        proyectoRequest.setTime(5.0f);
        proyectoRequest.setComunidadAutonoma(1);
        proyectoRequest.setProvincia(12);
        proyectoRequest.setMunicipio(123);
        proyectoRequest.setDescripcion("Dummy Description");
        return proyectoRequest;
    }
    
    
  
    
}
