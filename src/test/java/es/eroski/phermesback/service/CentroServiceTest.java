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
import org.springframework.context.annotation.ComponentScan;

import es.eroski.oinarri.library.ldaplogin.lib.user.domain.User;
import es.eroski.phermesback.dto.CentroFicticio;
import es.eroski.phermesback.dto.EstadoCentro;
import es.eroski.phermesback.dto.Rotulo;
import es.eroski.phermesback.dto.TipoFormato;
import es.eroski.phermesback.dto.TipoParking;
import es.eroski.phermesback.dto.TipoVariacion;
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

//@SpringBootTest
//@Transactional
@ComponentScan("es.eroski.phermesback.service.CentroService")
public class CentroServiceTest {
    
    private static final Logger logger = LogManager.getLogger(CentroServiceTest.class);

    @InjectMocks
    private CentroServiceImpl centroService; 
    
    @Mock
    private CentroFicticioRepository centroFicticioRepository;
    
    @Mock
    private CentroFicticioMapper centroFicticioMapper;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    /*@Test
    public void TestCentroFicticio() {
        logger.info("TEST: testAltaCentro");
        Authentication authentication = new UsernamePasswordAuthenticationToken("i2928", null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		CentroFicticioRequest cfr = new CentroFicticioRequest();
		
		cfr.setName("TestCentroFicticio");
		cfr.setRotulo(1);
		cfr.setTipoFormato(10);
		cfr.setType(1);
		cfr.setAccesoParking(0);
		cfr.setComunidadAutonoma(1);
		cfr.setDireccion("");
		cfr.setLatitud(10.454f);
		cfr.setLongitud(10.454f);
		cfr.setMotivoCreacion(1);

		CentroFicticio cf = centroService.createCentroFicticio(cfr);
		
		assertNotNull(cf);
    }*/
    
    @Test
    public void testCentroFicticioGet() {
		Mockito.when(centroFicticioRepository.findById(anyLong())).thenReturn(this.generateCentroFicticioEntity());
		
		Mockito.when((centroFicticioMapper.toDto(any(CentroFicticioEntity.class)))).thenReturn(this.generateCentroFicticio());
		
		CentroFicticio centro = centroService.getCentroFicticio(1L);
		
		assertNotNull(centro.getInsertDate());
    }
    
    
    
    private Optional<CentroFicticioEntity> generateCentroFicticioEntity() {
    	CentroFicticioEntity centro = new CentroFicticioEntity();
         centro.setId(1L);
         centro.setName("Nombre del Centro");
         centro.setLatitud(40.0f);
         centro.setLongitud(-3.0f);
         //centro.setComunidadAutonoma(1);
         //centro.setProvincia(2);
         //centro.setMunicipio(3);
         centro.setDireccion("Dirección del Centro");
         centro.setMetrosCuadrados(100.0f);
         centro.setLocalizacion(1); 
         centro.setAccesoParking(new TipoParkingEntity(1, "Parking 1", false)); 
         centro.setTipoFormato(new TipoFormatoEntity(1, "Formato 1", false)); 
         centro.setRotulo(new RotuloEntity(1, "Parking 1", false)); 
         centro.setEstado(new EstadoCentroEntity(1, "Parking 1", false)); 
         centro.setMotivoCreacion(new TipoVariacionEntity(1, "Parking 1", false)); 
         //centro.setCentroEscenario(new CentroEscenarioEntity()); 
         User user = new User();
         user.setUsername("i2222");
         centro.setAuditable(new AuditableEntity(user, Instant.now(), user, Instant.now())); 
         
         return Optional.of(centro);
    	
    }
    
    private CentroFicticio generateCentroFicticio() {
    	CentroFicticio centro = new CentroFicticio();
        centro.setId(1L);
        centro.setName("Dummy Centro");
        centro.setLatitud(40.7128f);
        centro.setLongitud(-74.0060f);
       // centro.setComunidadAutonoma(1);
        //&centro.setProvincia(12);
       // centro.setMunicipio(123);
        centro.setDireccion("Dummy Address");
        centro.setAccesoParking(new TipoParking());
        centro.setTipoFormato(new TipoFormato());
        centro.setRotulo(new Rotulo());
        centro.setEstado(new EstadoCentro());
        centro.setLocalizacion(1);
        centro.setMetrosCuadrados(100.5f);
        centro.setMotivoCreacion(new TipoVariacion());
        
        return centro;
    	
    }
    
}
