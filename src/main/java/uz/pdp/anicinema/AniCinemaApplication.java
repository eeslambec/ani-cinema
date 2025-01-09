package uz.pdp.anicinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties
public class AniCinemaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AniCinemaApplication.class, args);
    }
}
