package es.eroski.phermesback.config;
import es.eroski.oinarri.starter.springdata.starter.constant.SpringDataConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = SpringDataConstants.General.OINARRI_ENTITY_MANAGER_FACTORY_QUALIFIER,
        transactionManagerRef = SpringDataConstants.General.OINARRI_TRANSACTION_MANAGER_QUALIFIER,
        basePackages = "es.eroski.phermesback"
)
@EntityScan(basePackages = "es.eroski.phermesback")
public class OinarriDefaultDatasourceConfiguration {
    /*
        Oinarri's default datasource configuration.
        By default, this class does two things:
        Enables entity scanning from the root package of the project (es.eroski.exampleapp).
        Enables spring data jpa repositories scan from the root package of the project (es.eroski.exampleapp).
        With this configuration, your entities and jpa repositories will be scanned and used by the default Oinarri datasource.
        Keep in mind that if the root packages changes, the new package should be specified in the @EntityScan and @EnableJpaRepositories annotations of this class.
        If your application uses multiple datasources, and you don't want to use the Oinarri's default datasource,
        feel free to edit the package scanning to fit your requirements, even deleting this class if it isn't useful
        (the default datasource will still be used internally by oinarri).
     */
}
 