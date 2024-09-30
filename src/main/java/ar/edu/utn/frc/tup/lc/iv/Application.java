package ar.edu.utn.frc.tup.lc.iv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main class.
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class Application {

    /**
     * Main program.
     * @param args application args
     */
    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);
    }
}
