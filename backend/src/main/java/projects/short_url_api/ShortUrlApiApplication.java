package projects.short_url_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShortUrlApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortUrlApiApplication.class, args);
	}

}
