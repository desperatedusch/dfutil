package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import de.dfutil.dao.jpa.*;
import de.dfutil.entities.jpa.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;

@Service
public class Parser {

    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    private final Postprocessing postprocessing;
    private final KgRowRepository kgRowRepository;
    private final ObRowRepository obRowRepository;
    private final OrRowRepository orRowRepository;
    private final PlRowRepository plRowRepository;
    private final SbRowRepository sbRowRepository;
    private long linesProcessed = 0;

    public Parser(SbRowRepository sbRowRepository, PlRowRepository plRowRepository, OrRowRepository orRowRepository, ObRowRepository obRowRepository, KgRowRepository kgRowRepository, Postprocessing postprocessing) {
        this.sbRowRepository = sbRowRepository;
        this.plRowRepository = plRowRepository;
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.kgRowRepository = kgRowRepository;
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
        }
    }

    public void fromFile(Path path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile(), Charset.forName("Cp850")))) {
            var stopwatch = Stopwatch.createStarted();
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
            postprocessing.deleteProcessedInputSources(path);
        } catch (IOException e) {
            log.error("Parsing file failed: {}", path);
            postprocessing.parsingFailed(path, null);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public long rowCount() {
        return linesProcessed;
    }

}
