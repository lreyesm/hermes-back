package es.eroski.phermesback;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.eroski.caai.schema.lpuapi.LpuEncolarRequestType;
import es.eroski.caai.schema.lpuapi.LpuEncolarRequestType.Parametros.Lista;
import es.eroski.caai.schema.lpuapi.LpuEncolarResponse;
import es.eroski.caai.schema.lpuapi.ObjectFactory;
import es.eroski.caai.wsdl.lpuapiservicev1.LpuApiPortType;
import es.eroski.caai.wsdl.lpuapiservicev1.LpuApiService;
import jakarta.xml.ws.BindingProvider;

//import es.eroski.phermesback.client.LpuEncolarRequestType;
//import es.eroski.phermesback.client.LpuEncolarResponse;


@SpringBootApplication
@EnableAutoConfiguration
public class Hermes {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Hermes.class);

	
	public static void main(String[] args) {
		SpringApplication.run(Hermes.class, args);
	}

}
