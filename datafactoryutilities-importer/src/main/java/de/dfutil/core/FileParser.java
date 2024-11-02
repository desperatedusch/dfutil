package de.dfutil.core;

import de.dfutil.events.RowParsedEvent;
import de.dfutil.helpers.measurement.LogExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class FileParser {

    private static final Logger log = LoggerFactory.getLogger(FileParser.class);

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public FileParser() {}

    @LogExecutionTime
    public void parseFileWithBufferedReader(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Publish each line to event handling if row is not empty
                if (!ObjectUtils.isEmpty(line))
                    eventPublisher.publishEvent(new RowParsedEvent(line));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
