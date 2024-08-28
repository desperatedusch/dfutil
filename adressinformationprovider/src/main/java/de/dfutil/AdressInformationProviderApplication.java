package de.dfutil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class AdressInformationProviderApplication {

	private static final Logger log = LoggerFactory.getLogger(AdressInformationProviderApplication.class);

	public static void main(String[] args) {
		log.info("App started...");
		SpringApplication.run(AdressInformationProviderApplication.class, args);
		log.info("App stopped...");
	}
}
