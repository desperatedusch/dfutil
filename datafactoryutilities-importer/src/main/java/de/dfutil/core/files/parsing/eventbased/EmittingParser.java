package de.dfutil.core.files.parsing.eventbased;

import com.google.common.base.Stopwatch;
import de.dfutil.core.files.Postprocessing;
import de.dfutil.core.files.parsing.Parser;
import de.dfutil.events.FileParsingFinishedEvent;
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

    private long lineCounter = 0;

    private final ApplicationEventPublisher eventPublisher;

    private final Postprocessing postprocessing;

    public EmittingParser(Postprocessing postprocessing, ApplicationEventPublisher eventPublisher) {
        this.postprocessing = postprocessing;
        this.eventPublisher = eventPublisher;
    }

    public void fromFile(Path path) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        stopwatch.start();
        long duration;
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            log.debug("Parsing file: {}", path);
            String line;
            while ((line = br.readLine()) != null) {
                // Publish each line to event handler until Reader is empty
                if (!line.isEmpty()) {
                    eventPublisher.publishEvent(new RowParsedEvent(line));
                    lineCounter++;
                }
            }
            eventPublisher.publishEvent(new FileParsingFinishedEvent(rowCount()));
            stopwatch.stop();
            duration = stopwatch.elapsed().toMillis();
            log.info("Successfully parsed file {} within {} ms", path, duration);
            postprocessing.parsedSuccessfully(path, duration);
        } catch (IOException e) {
            log.error("Parsing file failed: {}", path);
            duration = stopwatch.elapsed().toMillis();
            postprocessing.parsingFailed(path, duration);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public long rowCount() {
        return lineCounter;
    }
}
