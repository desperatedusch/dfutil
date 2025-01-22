package de.dfutil.core.files.parsing.eventbased;

import de.dfutil.core.files.Postprocessing;
import de.dfutil.core.files.parsing.Parser;
import de.dfutil.events.RowParsedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

@Service
@Profile({"eventbased-persisting", "!procedural-persisting "})
public class EmittingParser implements Parser {

    private static final Logger log = LoggerFactory.getLogger(EmittingParser.class);

    private final ApplicationEventPublisher eventPublisher;
    private final Postprocessing postprocessing;

    public EmittingParser(Postprocessing postprocessing, ApplicationEventPublisher eventPublisher) {
        this.postprocessing = postprocessing;
        this.eventPublisher = eventPublisher;
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
            log.info("Successfully parsed file: {}", path);
            postprocessing.proccessingSuccessfull(path);
        } catch (IOException e) {
            log.error("Parsing file failed: {}", path);
            postprocessing.proccessingFailed(path);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
