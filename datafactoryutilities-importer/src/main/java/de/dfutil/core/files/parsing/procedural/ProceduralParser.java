package de.dfutil.core.files.parsing.procedural;

import de.dfutil.core.files.Postprocessing;
import de.dfutil.core.files.parsing.Parser;
import de.dfutil.dao.jpa.*;
import de.dfutil.entities.jpa.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final KgRowRepository kgRowRepository;
    private final ObRowRepository obRowRepository;
    private final OrRowRepository orRowRepository;
    private final PlRowRepository plRowRepository;
    private final SbRowRepository sbRowRepository;
    private final Postprocessing postprocessing;

    public ProceduralParser(Postprocessing postprocessing,
                            KgRowRepository kgRowRepository,
                            ObRowRepository obRowRepository,
                            OrRowRepository orRowRepository,
                            PlRowRepository plRowRepository,
                            SbRowRepository sbRowRepository) {

        this.postprocessing = postprocessing;

        this.kgRowRepository = kgRowRepository;
        this.obRowRepository = obRowRepository;
        this.orRowRepository = orRowRepository;
        this.plRowRepository = plRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    private void persist(String line) {
        String prefix = line.substring(0, 2);
        switch (prefix) {
            case "KG":
                KgRow kg = KgRow.parseFrom(line.getBytes());
                if (kgRowRepository.findById(kg.getKgRowId()).isEmpty())
                    kgRowRepository.save(kg);
                break;
            case "OB":
                ObRow ob = ObRow.parseFrom(line.getBytes());
                if (obRowRepository.findById(ob.getObRowId()).isEmpty())
                    obRowRepository.save(ob);
                break;
            case "OR":
                OrRow or = OrRow.parseFrom(line.getBytes());
                if (orRowRepository.findById(or.getOrRowId()).isEmpty())
                    orRowRepository.save(or);
                break;
            case "PL":
                PlRow pl = PlRow.parseFrom(line.getBytes());
                if (plRowRepository.findById(pl.getPlRowId()).isEmpty())
                    plRowRepository.save(pl);
            case "SB":
                SbRow sb = SbRow.parseFrom(line.getBytes());
                if (sbRowRepository.findById(sb.getSbRowId()).isEmpty())
                    sbRowRepository.save(sb);
                break;
            default:
                log.warn("Unsupported prefix : " + prefix);
        }

    }

    public void fromFile(Path path) {
        log.debug("Parsing file: {}", path);
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty())
                    persist(line);
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
