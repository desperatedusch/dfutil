package de.dfutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;


@SpringBootApplication
public class AddressInformationProviderApplication {

	private static final Logger log = LoggerFactory.getLogger(AddressInformationProviderApplication.class);

	public static void main(String[] args) {
		log.info("AddressInformationProviderApplication started...");
		new SpringApplicationBuilder(AddressInformationProviderApplication.class)
				.beanNameGenerator(new FullyQualifiedAnnotationBeanNameGenerator()).build().run(args);
	}
}
