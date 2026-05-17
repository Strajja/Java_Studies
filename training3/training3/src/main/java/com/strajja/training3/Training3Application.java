package com.strajja.training3;

import com.strajja.training3.config.Burger;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class Training3Application  implements CommandLineRunner {

	private final Burger burger;

	public Training3Application(Burger burger) {
		this.burger = burger;
	}

	public static void main(String[] args) {
		SpringApplication.run(Training3Application.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		log.info(String.format("I want a burger with meat %s, sauce %s and salad %s.",
				burger.getMeat(), burger.getSauce(), burger.getSalad()
				));
	}
}
