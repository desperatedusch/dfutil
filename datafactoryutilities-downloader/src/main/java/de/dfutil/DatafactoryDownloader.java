package de.dfutil;

import de.dfutil.downloading.Archives;
import de.dfutil.downloading.DatafactoryDownloaderConfigurationProperties;
import de.dfutil.downloading.DatafactoryResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DatafactoryDownloaderConfigurationProperties.class)
public class DatafactoryDownloader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryDownloader.class);

    private final DatafactoryResources datafactoryFiles;
    private final Archives archives;

    public DatafactoryDownloader(final DatafactoryResources datafactoryFiles, final Archives archives) {
        this.datafactoryFiles = datafactoryFiles;
        this.archives = archives;
    }

    public static void main(final String[] args) {
        new SpringApplicationBuilder(DatafactoryDownloader.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(final String... args) throws Exception {
        this.datafactoryFiles.fetch();
        this.archives.unzip();
    }

}
