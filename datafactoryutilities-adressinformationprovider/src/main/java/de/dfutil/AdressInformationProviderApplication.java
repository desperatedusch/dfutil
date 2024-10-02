package de.dfutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AdressInformationProviderApplication {

	private static final Logger log = LoggerFactory.getLogger(AdressInformationProviderApplication.class);

	public static void main(String[] args) {
		log.info("App started...");
		SpringApplication.run(AdressInformationProviderApplication.class, args);
	}
}
