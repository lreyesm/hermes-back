
 
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
 
import org.junit.jupiter.api.Test;
 
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {"spring.liquibase.enabled=false"})
public class Phermes {
 
   
}
