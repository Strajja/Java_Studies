package com.strajja.javaStudies;


import com.strajja.javaStudies.services.ColourPrinter;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.strajja.javaStudies.services.impl.ColourPrinterImpl;


@SpringBootApplication
@Log
public class JavaStudiesApplication implements CommandLineRunner {

	private ColourPrinter colourPrinter;

	public JavaStudiesApplication(ColourPrinter colourPrinter) {
		this.colourPrinter = colourPrinter;
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaStudiesApplication.class, args);
	}

	@Override
	public void run(final String... args){
			log.info(colourPrinter.print());
		}
}

