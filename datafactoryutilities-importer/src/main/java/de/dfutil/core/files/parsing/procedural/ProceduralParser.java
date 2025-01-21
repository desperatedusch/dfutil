package de.dfutil.core.files.parsing.procedural;

import de.dfutil.core.files.Postprocessing;
import de.dfutil.core.files.parsing.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

@Service
@Profile({"procedural-persisting ", "!eventbased-persisting"})
public class ProceduralParser implements Parser {

    private static final Logger log = LoggerFactory.getLogger(ProceduralParser.class);
    @Autowired
    private ProcessingStepChainFacade processing;


    private final Postprocessing postprocessing;

    public ProceduralParser(Postprocessing postprocessing) {
        this.postprocessing = postprocessing;
    }

    public void fromFile(Path path) {
        log.debug("Parsing file: {}", path);
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Publish each line to event handler until Reader is empty
                if (!line.isEmpty())
                    throw new UnsupportedOperationException("No CoR implemented to decide which dao is needed ");
            }
            postprocessing.proccessingSuccessfull(path);
            log.info("Successfully parsed file: {}", path);
        } catch (IOException e) {
            log.error("Parsing file failed: {}", path);
            postprocessing.proccessingFailed(path);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
