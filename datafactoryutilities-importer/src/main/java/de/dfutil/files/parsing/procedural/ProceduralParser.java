package de.dfutil.files.parsing.procedural;

import com.google.common.base.Stopwatch;
import de.dfutil.dao.jpa.*;
import de.dfutil.entities.jpa.*;
import de.dfutil.files.Postprocessing;
import de.dfutil.files.parsing.Parser;
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
@Profile({"procedural-importing ", "!eventbased-importing"})
public class ProceduralParser implements Parser {

    private static final Logger log = LoggerFactory.getLogger(ProceduralParser.class);
    private final Postprocessing postprocessing;
    private long linesProcessed = 0;
    @Autowired
    private KgRowRepository kgRowRepository;
    @Autowired
    private ObRowRepository obRowRepository;
    @Autowired
    private OrRowRepository orRowRepository;
    @Autowired
    private PlRowRepository plRowRepository;
    @Autowired
    private SbRowRepository sbRowRepository;

    public ProceduralParser(Postprocessing postprocessing) {
        this.postprocessing = postprocessing;
    }

    private void persist(String line) {
        String prefix = line.substring(0, 2);
        switch (prefix) {
            case "KG":
                KgRow kg = KgRow.parseFrom(line);
                if (kgRowRepository.findById(kg.getKgRowId()).isEmpty()) {
                    kgRowRepository.save(kg);
                    linesProcessed++;
                }
                break;
            case "OB":
                ObRow ob = ObRow.parseFrom(line);
                if (obRowRepository.findById(ob.getObRowId()).isEmpty()) {
                    obRowRepository.save(ob);
                    linesProcessed++;
                }
                break;
            case "OR":
                OrRow or = OrRow.parseFrom(line);
                if (orRowRepository.findById(or.getOrRowId()).isEmpty()) {
                    orRowRepository.save(or);
                    linesProcessed++;
                }
                break;
            case "PL":
                PlRow pl = PlRow.parseFrom(line);
                if (plRowRepository.findById(pl.getPlRowId()).isEmpty()) {
                    plRowRepository.save(pl);
                    linesProcessed++;
                }
                break;
            case "SB":
                SbRow sb = SbRow.parseFrom(line);
                if (sbRowRepository.findById(sb.getSbRowId()).isEmpty()) {
                    sbRowRepository.save(sb);
                    linesProcessed++;
                }
                break;
            default:
                log.warn("Unsupported prefix : {}", prefix);
        }
    }

    public void fromFile(Path path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            long duration;
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    persist(line);
                }
            }
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
        return linesProcessed;
    }

}
