package unitn.bonazzi.partiteweb.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("unitn.bonazzi.partiteweb")
public class PartiteWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartiteWebApplication.class, args);
    }

}
