package de.dfutil.downloader.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DatafactoryResources {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryResources.class);

    private final WebClient client;

    @Value("app.downloader.datafactory.resource.location")
    private String baseUrl;
    @Value("app.downloader.datafactory.resource.location")
    private String targetFolder;

    public DatafactoryResources() {
        this.client = WebClient.builder().baseUrl(baseUrl)
                .exchangeStrategies(useMaxMemory())
                .build();
    }

    public long fetch() throws IOException {
        Flux<DataBuffer> flux = client.get()
                .retrieve()
                .bodyToFlux(DataBuffer.class);
        Path path = Paths.get(targetFolder);
        DataBufferUtils.write(flux, path)
                .block();
        return Files.size(path);
    }

    private ExchangeStrategies useMaxMemory() {
        long totalMemory = Runtime.getRuntime().maxMemory();

        return ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs()
                        .maxInMemorySize((int) totalMemory)
                )
                .build();
    }
}
