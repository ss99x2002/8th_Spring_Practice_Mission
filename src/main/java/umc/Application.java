package umc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
// Spring application 자체도 JpaAuditing사용이 가능하도록
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
