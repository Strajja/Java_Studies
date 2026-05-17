package com.strajja.training1;

import com.strajja.training1.interfaces.Hello;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class Training1Application implements CommandLineRunner{

	private final Hello hello;

	public Training1Application(Hello hello) {
		this.hello = hello;
	}

	public static void main(String[] args) {
		SpringApplication.run(Training1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		hello.sayHello();
	}
}
