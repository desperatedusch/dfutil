package de.dfutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressInformationProvider {

    private static final Logger log = LoggerFactory.getLogger(AddressInformationProvider.class);

    public static void main(String[] args) {
        log.debug("AddressInformationProviderApplication started...");
        SpringApplication.run(AddressInformationProvider.class, args);
    }
}
