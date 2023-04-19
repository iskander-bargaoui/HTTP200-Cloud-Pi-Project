package tn.esprit.pibakcend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class PiBakcendApplication {
    public static void main(String[] args) {
        SpringApplication.run(PiBakcendApplication.class, args);
    }
}
