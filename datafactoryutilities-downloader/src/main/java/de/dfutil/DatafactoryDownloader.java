package de.dfutil;

import de.dfutil.core.DatafactoryResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DatafactoryDownloader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryDownloader.class);

    @Autowired
    private DatafactoryResources datafactoryFiles;

    public DatafactoryDownloader() {
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(DatafactoryDownloader.class).web(WebApplicationType.NONE).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        datafactoryFiles.fetch();
    }

}
