package com.strajja.training2;

import com.strajja.training2.animals.Animals;
import com.strajja.training2.animals.Cat;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class Training2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Training2Application.class, args);
	}

	private final Animals animals;

	public Training2Application(Animals animals) {
		this.animals = animals;
	}

	@Override
	public void run(String... args) throws Exception {
        log.info(animals.AnimalSounds());
    }
}
