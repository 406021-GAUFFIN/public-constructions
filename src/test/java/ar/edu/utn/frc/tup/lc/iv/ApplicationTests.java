package ar.edu.utn.frc.tup.lc.iv;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081",
        "accesses.url = http://localhost:8085"
})
class ApplicationTests {

    @Test
    void contextLoads() {
    }

}
