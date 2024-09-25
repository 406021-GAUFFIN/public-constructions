package ar.edu.utn.frc.tup.lc.iv;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
})
class ApplicationTests {

    @Test
    void contextLoads() {
    }

}
