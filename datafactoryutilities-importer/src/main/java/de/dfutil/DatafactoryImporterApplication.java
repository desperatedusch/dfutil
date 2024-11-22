package de.dfutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatafactoryImporterApplication {

	private static final Logger log = LoggerFactory.getLogger(DatafactoryImporterApplication.class);

	public static void main(String[] args) {

		log.info("Importer app started...");
		printPropertiesAndArguments();
		SpringApplication.run(DatafactoryImporterApplication.class, args);

		//new DataFactoryFileParser().parseFile
		log.info("Importer app stopped...");
	}

	private static void printPropertiesAndArguments() {
	}
}