package de.dfutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication
public class DatafactoryImporterApplication {

	private static final Logger log = LoggerFactory.getLogger(DatafactoryImporterApplication.class);

	public static void main(String[] args) {

		log.info("Importer app started...");
		new SpringApplicationBuilder(DatafactoryImporterApplication.class)
				.beanNameGenerator(new FullyQualifiedAnnotationBeanNameGenerator()).build().run(args);
		//TODO implement serious parameter handling
		//new DataFactoryFileParser().parseFile
		log.info("Importer app stopped...");
	}
}