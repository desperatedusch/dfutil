package de.dfutil.importing.parsing.eventbased;

import com.google.common.base.Stopwatch;
import de.dfutil.events.FileParsingFinishedEvent;
import de.dfutil.events.RowParsedEvent;
import de.dfutil.importing.Postprocessing;
import de.dfutil.importing.parsing.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

@Service
@Profile({"eventbased-importing", "!procedural-importing "})
public class EmittingParser implements Parser {

    private static final Logger log = LoggerFactory.getLogger(EmittingParser.class);

    private long lineCounter = 0;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private final Postprocessing postprocessing;

    public EmittingParser(Postprocessing postprocessing) {
        this.postprocessing = postprocessing;
    }

    public void fromFile(Path path) {
        Stopwatch stopwatch = Stopwatch.createStarted();
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
            postprocessing.parsingFailed(path, null);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public long rowCount() {
        return lineCounter;
    }

}
