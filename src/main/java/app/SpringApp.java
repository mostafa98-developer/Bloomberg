package app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import todos.TodoRepository;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//(scanBasePackages = {"org.app.controller","todos"})
@ComponentScan({"org.app.controller","todos","secuirty"})
@EnableMongoRepositories(basePackageClasses = {TodoRepository.class})

public class SpringApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);

	}

}
