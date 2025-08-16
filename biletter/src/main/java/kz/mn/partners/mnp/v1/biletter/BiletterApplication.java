package kz.mn.partners.mnp.v1.biletter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BiletterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiletterApplication.class, args);
	}

}
