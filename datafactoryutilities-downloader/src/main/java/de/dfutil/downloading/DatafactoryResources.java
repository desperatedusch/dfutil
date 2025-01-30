package de.dfutil.downloading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class DatafactoryResources {

    private static final Logger log = LoggerFactory.getLogger(DatafactoryResources.class);

    private final WebClient client;

    @Value("${app.downloader.resource.url}")
    @NonNull
    private String resourceUrl;
    @Value("${app.downloader.target.destination.archived}")
    @NonNull
    private String targetFolder;
    @Value("${app.downloader.user}")
    @NonNull
    private String user;
    @Value("${app.downloader.password}")
    @NonNull
    private String password;

    public DatafactoryResources() {
        this.client = WebClient.builder().baseUrl(resourceUrl)
                .exchangeStrategies(useMaxMemory())
                .build();
    }

    public long fetch() throws IOException {
        String basicAuthHeader = "basic " + Base64.getEncoder().encodeToString((user + ":" + password).getBytes());
        Flux<DataBuffer> flux = client
                .get()
                .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
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
                        .maxInMemorySize((int) totalMemory))
                .build();
    }

}
