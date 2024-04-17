
/**
 * @file
 * @brief Implementación de la clase ItemSegmentServiceImpl.
 */
package es.eroski.phermesback.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import es.eroski.caai.schema.lpuapi.LpuEncolarRequestType;
import es.eroski.caai.schema.lpuapi.LpuEncolarResponse;
import es.eroski.caai.schema.lpuapi.ObjectFactory;
import es.eroski.caai.wsdl.lpuapiservicev1.LpuApiPortType;
import es.eroski.caai.wsdl.lpuapiservicev1.LpuApiService;
import es.eroski.phermesback.dto.Ejecucion;
import es.eroski.phermesback.dto.Escenario;
import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.request.EjecucionRealizadaRequest;
import es.eroski.phermesback.mapper.EjecucionMapper;
import es.eroski.phermesback.mapper.EscenarioMapper;
import es.eroski.phermesback.mapper.ProyectoMapper;
import es.eroski.phermesback.model.EjecucionEntity;
import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.ProyectoEntity;
import es.eroski.phermesback.persistence.EjecucionRepository;
import es.eroski.phermesback.persistence.EscenarioRepository;
import es.eroski.phermesback.persistence.EstadoEjecucionRepository;
import es.eroski.phermesback.persistence.ProyectoRepository;
import es.eroski.phermesback.persistence.specifications.EjecucionEspecification;
import es.eroski.phermesback.persistence.specifications.EscenarioEspecifications;
import es.eroski.phermesback.security.util.SecurityUtil;
import es.eroski.phermesback.service.EjecucionService;
import es.eroski.phermesback.service.EscenarioService;
import jakarta.xml.ws.BindingProvider;

@Service
public class EjecucionServiceImpl implements EjecucionService {
	
	@Autowired
	private EscenarioRepository escenarioRepository;
		
	@Autowired
	private EstadoEjecucionRepository estadoEjecucionRepository;
		
	@Autowired
	private SecurityUtil securityUtil;
	
	@Autowired
	private EjecucionRepository ejecucionRepository;
	
	@Autowired
	private EjecucionMapper ejecucionMapper;
	
	@Autowired
	private ProyectoMapper proyectoMapper;
	
	@Autowired
	private EscenarioMapper escenarioMapper;
	
	@Autowired
	private AmazonS3 amazonS3Client;
	
    @Value("${netapp.bucket}")
    private String s3bucket;
	
	
	
	public EjecucionServiceImpl() { 
		
	}

	@Override
	public Ejecucion getEjecucion(Long id) {
		EjecucionEntity ejecucionEnt = ejecucionRepository.findById(id).get();
		Ejecucion ejecucion = ejecucionMapper.toDto(ejecucionEnt);
		ejecucion = this.mountDto(ejecucion, ejecucionEnt);
		return ejecucion;
	}

	@Override
	public PageCustom getEjecucionesByEscenario(Long escenarioId, String name, Integer estado, Long id, List<Integer> comunidades, List<Integer> municipios, 
			List<Integer> provincias, Date startExecutionDate, Date endExecutionDate, String proyecto, String username,  
			Long idProyecto, Long numEjecuciones, LocalTime executionHour, LocalTime executionFinishedHour, int page, int size) {
		//EscenarioEntity escenario = escenarioRepository.findById(escenarioId).get();
		//List<EjecucionEntity> entities = ejecucionRepository.findAllByEscenario(escenario);
		
		Specification<EjecucionEntity> spec = EjecucionEspecification.buildSpecification(name, estado, id, comunidades, municipios, provincias, 
				startExecutionDate, endExecutionDate, proyecto, username, idProyecto, escenarioId, executionHour);  
		Pageable PageCustomable = PageRequest.of(page, size);
		Page<EjecucionEntity> entities = ejecucionRepository.findAll(spec, PageCustomable);
		List<Ejecucion> ejecuciones = new ArrayList<>();
		for (EjecucionEntity ejecucion: entities) {
			if((numEjecuciones == null || ejecucionRepository.findAllByEscenario(ejecucion.getEscenario()).size() == numEjecuciones) 
					&& (executionHour == null || isSameHourAndMinute(ejecucion.getAuditable().getInsertDate(), executionHour)) 
					&& (executionFinishedHour == null || isSameHourAndMinute(ejecucion.getFecha_resultado(), executionFinishedHour))) {
					Ejecucion dto = ejecucionMapper.toDto(ejecucion);
					dto = this.mountDto(dto, ejecucion);
					ejecuciones.add(dto);
			}

		}
		return new PageCustom(ejecuciones, entities.getTotalElements());
	}

	@Override
	public PageCustom getAll(String name, Integer estado, Long id, List<Integer> comunidades, List<Integer> municipios, 
			List<Integer> provincias, Date startExecutionDate, Date endExecutionDate, String proyecto, String username, 
			Long idProyecto, Long numEjecuciones, LocalTime executionHour, LocalTime executionFinishedHour, int page, int size) {
		
		Specification<EjecucionEntity> spec = EjecucionEspecification.buildSpecification(name, estado, id, comunidades, municipios, provincias, 
				startExecutionDate, endExecutionDate, proyecto, username, idProyecto, null, executionHour); 
		
		Pageable PageCustomable = PageRequest.of(page, size);
		Page<EjecucionEntity> entities = ejecucionRepository.findAll(spec, PageCustomable);
		List<Ejecucion> ejecuciones = new ArrayList<>();
		for (EjecucionEntity ejecucion: entities) {
			if((numEjecuciones == null || ejecucionRepository.findAllByEscenario(ejecucion.getEscenario()).size() == numEjecuciones) 
					&& (executionHour == null || isSameHourAndMinute(ejecucion.getAuditable().getInsertDate(), executionHour)) 
					&& (executionFinishedHour == null || isSameHourAndMinute(ejecucion.getFecha_resultado(), executionFinishedHour))) {
					Ejecucion dto = ejecucionMapper.toDto(ejecucion);
					dto = this.mountDto(dto, ejecucion);
					ejecuciones.add(dto);
			}
		}
		return new PageCustom(ejecuciones, entities.getTotalElements());
	}

	@Override
	public Ejecucion execute(Long idEscenario) {
		// Esto es una prueba, no el servicio real
		EscenarioEntity escenario = escenarioRepository.findById(idEscenario).get();
		EjecucionEntity entity = new EjecucionEntity();
		entity.setEscenario(escenario);
		entity.setEstado(estadoEjecucionRepository.findById(1).get());
		entity.setAuditable(securityUtil.generateAuditable());
		
		this.executeSoapRequest();
		
		entity = ejecucionRepository.save(entity);
		Ejecucion dto = ejecucionMapper.toDto(entity);
		dto = this.mountDto(dto, entity);
		return dto;
		
	}
	
	@Override
	public void executionFinished(EjecucionRealizadaRequest ejecucionRealizada, byte[] file) throws IOException {
		EjecucionEntity entity = ejecucionRepository.findById(ejecucionRealizada.getIdEjecucion()).get();
	
		String fileName = ejecucionRealizada.getIdProyecto( ) + "/" +ejecucionRealizada.getIdEscenario()+ "/" +ejecucionRealizada.getIdEjecucion() + "/" + new Date(); 
		
		
		try {
	           // Crear y configurar los metadatos del objeto
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.length);

            // Crear y configurar la solicitud de carga del objeto
            PutObjectRequest request = new PutObjectRequest(s3bucket, fileName, new ByteArrayInputStream(file), metadata);

            // Subir el archivo al bucket de S3
            amazonS3Client.putObject(request);
			System.out.println("Documento subido correctamente.");
		} catch (AmazonClientException e) {
			System.out.println("Ha ocurrido un error en la subida de NETAPP" + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.out.println("Error inesperado al subir el documento" + e.getMessage());
		}
		
		this.listObjects();
		entity.setResultado(fileName);
		entity.setFecha_resultado(new Date().toInstant());
		entity.setEstado(estadoEjecucionRepository.findById(2).get());
		entity = ejecucionRepository.save(entity);
			
	}
	
    public void listObjects() {

        ObjectListing listing = amazonS3Client.listObjects( s3bucket );
        List<S3ObjectSummary> summaries = listing.getObjectSummaries();
        summaries.forEach (s -> System.out.println("objeto: " + s.getKey()));

    }
	
		
	private void executeSoapRequest() {
		LpuApiService service = new LpuApiService();
		LpuApiPortType port = service.getLpuApiPort();
		
		 // Set basic authentication credentials
	    String username = "hermesinetum";
	    String password = "t]ZNdXzsJH(&";
	    setBasicAuth((BindingProvider) port, username, password);

	    // Invoke the SOAP operation
	    LpuEncolarRequestType request = new LpuEncolarRequestType();
	    ObjectFactory objectFactory = new ObjectFactory();
	    request.setUsuario("S2469");
		request.setPrograma("PRNODO");
		request.setProceso("PRUAPILPU");
		request.setGrupo("EySS Planificacion");
		request.setFlagMail(false);
		request.setAplicacion("LPUACCIONES");
		
		/*LpuEncolarRequestType.Parametros parametros = new LpuEncolarRequestType.Parametros();
		LpuEncolarRequestType.Parametros parametro1 = new LpuEncolarRequestType.Parametros();
		LpuEncolarRequestType.Parametros parametro2 = new LpuEncolarRequestType.Parametros();
		String cadena1 = "tmp_path=/tmp/;tmp_data_file=H_LOCALIZACIONES_DUMMY_TMP;IdProyecto=1;IdEscenario=2;IdEjecucion=3;IdTienda=9999;RotuloCompetencia=Tortulo_Dummy</lpu:Parametro";
		String cadena2 = "tmp_path_file=/tmp/H_LOCALIZACIONES_DUMMY_TMP;tmp_path_file_copia=/tmp/H_LOCALIZACIONES_DUMMY_COPIA_TMP";
		parametro1.setCadena(objectFactory.createLpuEncolarRequestTypeParametrosCadena(cadena1));
		parametro2.setCadena(objectFactory.createLpuEncolarRequestTypeParametrosCadena(cadena2));
		Lista lista = objectFactory.createLpuEncolarRequestTypeParametrosLista();
		lista.getParametro().add(parametro1);
		lista.getParametro().add(parametro2);
		parametros.setLista(lista);
		request.setParametros(parametros);*/
		LpuEncolarResponse response = port.encolar(request);
		System.out.println(response);
	}
	
	private static void setBasicAuth(BindingProvider port, String username, String password) {
	    // Set basic authentication credentials
	    Map<String, Object> requestContext = port.getRequestContext();
	    requestContext.put(BindingProvider.USERNAME_PROPERTY, username);
	    requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
	}
	
	private Ejecucion mountDto(Ejecucion dto, EjecucionEntity entity) {
		dto.setNumEjecuciones(ejecucionRepository.findAllByEscenario(entity.getEscenario()).size());
		dto.setProyecto(proyectoMapper.toDto(entity.getEscenario().getProyecto()));
		dto.setEscenario(escenarioMapper.toDto(entity.getEscenario()));
		dto.setInsertDate(entity.getAuditable().getInsertDate().plus(2, ChronoUnit.HOURS));	
		if (entity.getFecha_resultado() != null)dto.setFecha_resultado(entity.getFecha_resultado().plus(2, ChronoUnit.HOURS));
		dto.setInsertUser(entity.getAuditable().getInsertUser().getUsername());
		
		return dto;
	}

	@Override
	public byte[] downloadFile(Long idEjecucion) throws IOException {
        try {
        	EjecucionEntity entity = ejecucionRepository.findById(idEjecucion).get();
            // Obtener el objeto desde Amazon S3
            S3Object s3Object = amazonS3Client.getObject(s3bucket, entity.getResultado());

            // Leer los bytes del objeto
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Cerrar el stream de entrada y el stream de salida
            inputStream.close();
            outputStream.close();

            // Devolver los bytes del archivo
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new IOException("Error al recuperar los bytes del archivo desde Amazon S3: " + e.getMessage());
        }
    }
	
	

    private boolean isSameHourAndMinute(Instant instant, LocalTime time) {
    	if (instant == null) return false;
    	instant = instant.plus(2, ChronoUnit.HOURS);
        int hour = instant.atZone(ZoneOffset.UTC).getHour();
        int minute = instant.atZone(ZoneOffset.UTC).getMinute();
        
        
        return hour == time.getHour() && minute == time.getMinute();
    }
}
