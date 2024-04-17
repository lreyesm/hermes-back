package es.eroski.phermesback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.S3ClientOptions;


@Configuration
public class S3Config {

	@Value("${netapp.url}")
	    private String s3Url;

    @Value("${netapp.key}")
    private String s3Key;

    @Value("${netapp.secret}")
    private String s3Secret;
    
    

    @Bean
    public AmazonS3 amazonS3Client() {
        // Crear las credenciales
        BasicAWSCredentials credentials = new BasicAWSCredentials(s3Key, s3Secret);

        // Crear y configurar el cliente de Amazon S3
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3Url, null))
                .withPathStyleAccessEnabled(true) // Configurar acceso mediante path style
                .build();



        return s3;
    }
}