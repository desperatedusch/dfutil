package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import de.dfutil.dao.*;
import de.dfutil.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;

@Service
public class Parsing {

    private static final Logger log = LoggerFactory.getLogger(Parsing.class);

    private final Postprocessing postprocessing;

    private final KgRowRepository kgRowRepository;
    private final ObRowRepository obRowRepository;
    private final OrRowRepository orRowRepository;
    private final PlRowRepository plRowRepository;
    private final SbRowRepository sbRowRepository;

    private long rowsProcessed;

    public Parsing(SbRowRepository sbRowRepository, PlRowRepository plRowRepository, OrRowRepository orRowRepository, ObRowRepository obRowRepository, KgRowRepository kgRowRepository, Postprocessing postprocessing) {
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
                KgRowEntity kg = KgRowEntity.parseFrom(line);
                if (kgRowRepository.findById(kg.getKgRowId()).isEmpty()) {
                    kgRowRepository.save(kg);
                    rowsProcessed++;
                }
                break;
            case "OB":
                ObRowEntity ob = ObRowEntity.parseFrom(line);
                if (obRowRepository.findById(ob.getObRowId()).isEmpty()) {
                    obRowRepository.save(ob);
                    rowsProcessed++;
                }
                break;
            case "OR":
                OrRowEntiy or = OrRowEntiy.parseFrom(line);
                if (orRowRepository.findById(or.getOrRowId()).isEmpty()) {
                    orRowRepository.save(or);
                    rowsProcessed++;
                }
                break;
            case "PL":
                PlRowEntity pl = PlRowEntity.parseFrom(line);
                if (plRowRepository.findById(pl.getPlRowId()).isEmpty()) {
                    plRowRepository.save(pl);
                    rowsProcessed++;
                }
                break;
            case "SB":
                SbRowEntity sb = SbRowEntity.parseFrom(line);
                if (sbRowRepository.findById(sb.getSbRowId()).isEmpty()) {
                    sbRowRepository.save(sb);
                    rowsProcessed++;
                }
                break;
            default:
        }
    }

    public void fromFile(Path path) {
        rowsProcessed = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile(), Charset.forName("Cp850")))) {
            var stopwatch = Stopwatch.createStarted();
            long duration;
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    persist(line);
                }
            }
            duration = stopwatch.stop().elapsed().toMillis();
            log.info("Successfully parsed, imported {} rows with new/updated content, from {} within {} ms", rowsProcessed, path, duration);
            postprocessing.parsedSuccessfully(path, duration);
            postprocessing.deleteProcessedInputSources(path);
        } catch (IOException e) {
            log.error("Parsing {} failed", path);
            postprocessing.parsingFailed(path, null);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
