package ar.edu.utn.frc.tup.lc.iv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;

/**
 * Main class.
 */
@SpringBootApplication
@EnableJpaAuditing
public class Application {

    /**
     * Main program.
     * @param args application args
     */
    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);
    }
}
