/*package es.eroski.phermesback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.eroski.phermesback.service.HermesClient;

@Configuration
public class HermesClientConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("es.eroski.phermesback.client");
		return marshaller;
	}

	@Bean
	public HermesClient countryClient(Jaxb2Marshaller marshaller) {
		HermesClient client = new HermesClient();
		client.setDefaultUri("http://gersoa1.eroski.es:39080/LpuApiService/LpuApiPort/2013/03?WSDL");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}*/
