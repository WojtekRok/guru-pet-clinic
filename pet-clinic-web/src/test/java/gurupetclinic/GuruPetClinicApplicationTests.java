package gurupetclinic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.wojtekrok.gurupetclinic.GuruPetClinicApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GuruPetClinicApplication.class)
class GuruPetClinicApplicationTests {

    @Test
    void contextLoads() {
    }

}
