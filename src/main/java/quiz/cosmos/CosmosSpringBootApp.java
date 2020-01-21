package quiz.cosmos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import quiz.cosmos.model.Employee;

@SpringBootApplication
public class CosmosSpringBootApp {
	
	public static void main(String[] args) {
		
		SpringApplication.run(CosmosSpringBootApp.class, args);

	}
}