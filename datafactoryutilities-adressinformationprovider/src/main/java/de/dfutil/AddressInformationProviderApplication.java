package de.dfutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AddressInformationProviderApplication {

	private static final Logger log = LoggerFactory.getLogger(AddressInformationProviderApplication.class);

	public static void main(String[] args) {
		log.info("AddressInformationProviderApplication started...");
		SpringApplication.run(AddressInformationProviderApplication.class, args);
	}
}
