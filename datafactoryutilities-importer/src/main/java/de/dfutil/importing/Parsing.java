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

    private final KgRowRepository kgRowRepository;
    private final ObRowRepository obRowRepository;
    private final OrRowRepository orRowRepository;
    private final PlRowRepository plRowRepository;
    private final SbRowRepository sbRowRepository;

    private final Postprocessing postprocessing;

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
                if (!kgRowRepository.existsById(kg.getKgRowId())) {
                    kgRowRepository.save(kg);
                    rowsProcessed++;
                }
                break;
            case "OB":
                ObRowEntity ob = ObRowEntity.parseFrom(line);
                if (!obRowRepository.existsById(ob.getObRowId())) {
                    obRowRepository.save(ob);
                    rowsProcessed++;
                }
                break;
            case "OR":
                OrRowEntity or = OrRowEntity.parseFrom(line);
                if (!orRowRepository.existsById(or.getOrRowId())) {
                    orRowRepository.save(or);
                    rowsProcessed++;
                }
                break;
            case "PL":
                PlRowEntity pl = PlRowEntity.parseFrom(line);
                if (!plRowRepository.existsById(pl.getPlRowId())) {
                    plRowRepository.save(pl);
                    rowsProcessed++;
                }
                break;
            case "SB":
                SbRowEntity sb = SbRowEntity.parseFrom(line);
                if (!sbRowRepository.existsById(sb.getSbRowId())) {
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
            log.error("Parsing {} failed\n{}", path, e.getMessage());
            postprocessing.parsingFailed(path, null);
            throw new RuntimeException(e);
        }
    }

}
