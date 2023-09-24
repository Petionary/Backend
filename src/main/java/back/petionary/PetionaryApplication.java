package back.petionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PetionaryApplication {

    public static void main(String[] args) {
        System.out.println("젠킨스 테스트중입니다.");
        SpringApplication.run(PetionaryApplication.class, args);
    }

}
