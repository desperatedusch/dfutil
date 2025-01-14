package de.dfutil.core.files;

import de.dfutil.events.RowParsedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class Parsing {

    private static final Logger log = LoggerFactory.getLogger(Parsing.class);

    private final ApplicationEventPublisher eventPublisher;
    private final Postprocessing postprocessing;


    public Parsing(ApplicationEventPublisher eventPublisher, Postprocessing postprocessing) {
        this.eventPublisher = eventPublisher;
        this.postprocessing = postprocessing;
    }

    public void fromFile(Path path) {
        log.debug("Parsing file: {}", path);
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Publish each line to event handler until Reader is empty
                if (!line.isEmpty())
                    eventPublisher.publishEvent(new RowParsedEvent(line));
            }
            postprocessing.proccessingSuccessfull(path);
        } catch (IOException e) {
            log.error(e.getMessage());
            postprocessing.proccessingFailed(path);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
