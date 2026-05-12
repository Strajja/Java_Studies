package com.strajja.configuration;

import com.strajja.configuration.config.pizzaConfig;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class PizzaApplication implements CommandLineRunner {

	public pizzaConfig pizzaconfig;

	public PizzaApplication(pizzaConfig pizzaconfig){
		this.pizzaconfig=pizzaconfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(PizzaApplication.class, args);
	}

	@Override
	public void run(final String... args){
		log.info(String.format("I want a %s crust pizza, with %s and %s sauce",
				pizzaconfig.getCrust(),
				pizzaconfig.getTopping(),
				pizzaconfig.getSauce()
		));
	}
}
