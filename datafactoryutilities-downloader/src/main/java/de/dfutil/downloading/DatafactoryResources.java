package de.dfutil.downloading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@EnableConfigurationProperties(DatafactoryDownloaderConfigurationProperties.class)
public class DatafactoryResources {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryResources.class);

    private final DatafactoryDownloaderConfigurationProperties datafactoryDownloaderConfigurationProperties;

    private final WebClient client;

    public DatafactoryResources(final DatafactoryDownloaderConfigurationProperties datafactoryDownloaderConfigurationProperties) {
        this.datafactoryDownloaderConfigurationProperties = datafactoryDownloaderConfigurationProperties;
        client = WebClient.builder().baseUrl(datafactoryDownloaderConfigurationProperties.getResourceUrl())
                .exchangeStrategies(this.useMaxMemory())
                .build();
    }

    public long fetch() throws IOException {
        final String basicAuthHeader = "basic " + Base64.getEncoder().encodeToString((this.datafactoryDownloaderConfigurationProperties.getUser() + ":" + this.datafactoryDownloaderConfigurationProperties.getPassword()).getBytes(StandardCharsets.UTF_8));
        final Flux<DataBuffer> flux = this.client
                .get()
                .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
                .retrieve()
                .bodyToFlux(DataBuffer.class);
        final Path path = Paths.get(this.datafactoryDownloaderConfigurationProperties.getTargetFolder());
        DataBufferUtils.write(flux, path)
                .block();
        return Files.size(path);
    }

    private ExchangeStrategies useMaxMemory() {
        final long totalMemory = Runtime.getRuntime().maxMemory();

        return ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs()
                        .maxInMemorySize((int) totalMemory))
                .build();
    }

}
