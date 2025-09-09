package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import de.dfutil.dao.*;
import de.dfutil.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Service
@EnableConfigurationProperties(ImporterConfigurationProperties.class)
public class Parsing {

    private static final Logger log = LoggerFactory.getLogger(Parsing.class);
    private final ImporterConfigurationProperties importerConfigurationProperties;

    private final KgRowRepository kgRowRepository;
    private final ObRowRepository obRowRepository;
    private final OrRowRepository orRowRepository;
    private final PlRowRepository plRowRepository;
    private final SbRowRepository sbRowRepository;

    private final Postprocessing postprocessing;

    private final AtomicLong rowsProcessed = new AtomicLong(0);

    public Parsing(ImporterConfigurationProperties importerConfigurationProperties,
                   SbRowRepository sbRowRepository,
                   PlRowRepository plRowRepository,
                   OrRowRepository orRowRepository,
                   ObRowRepository obRowRepository,
                   KgRowRepository kgRowRepository,
                   Postprocessing postprocessing) {
        this.importerConfigurationProperties = importerConfigurationProperties;
        this.sbRowRepository = sbRowRepository;
        this.plRowRepository = plRowRepository;
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.kgRowRepository = kgRowRepository;
        this.postprocessing = postprocessing;
    }

    private void persist(String line, Set<KgRowEntity> kgBatch, Set<ObRowEntity> obBatch,
                         Set<OrRowEntity> orBatch, Set<PlRowEntity> plBatch, Set<SbRowEntity> sbBatch) {
        String prefix = line.substring(0, 2);
        switch (prefix) {
            case "KG":
                KgRowEntity kg = KgRowEntity.parseFrom(line);
                if (!kgRowRepository.existsById(kg.getKgRowId())) {
                    kgBatch.add(kg);
                    rowsProcessed.incrementAndGet();
                }
                break;
            case "OB":
                ObRowEntity ob = ObRowEntity.parseFrom(line);
                if (!obRowRepository.existsById(ob.getObRowId())) {
                    obBatch.add(ob);
                    rowsProcessed.incrementAndGet();
                }
                break;
            case "OR":
                OrRowEntity or = OrRowEntity.parseFrom(line);
                if (!orRowRepository.existsById(or.getOrRowId())) {
                    orBatch.add(or);
                    rowsProcessed.incrementAndGet();
                }
                break;
            case "PL":
                PlRowEntity pl = PlRowEntity.parseFrom(line);
                if (!plRowRepository.existsById(pl.getPlRowId())) {
                    plBatch.add(pl);
                    rowsProcessed.incrementAndGet();
                }
                break;
            case "SB":
                SbRowEntity sb = SbRowEntity.parseFrom(line);
                if (!sbRowRepository.existsById(sb.getSbRowId())) {
                    sbBatch.add(sb);
                    rowsProcessed.incrementAndGet();
                }
                break;
            default:
                break;
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void fromFile(Path path) {
        rowsProcessed.set(0);
        Set<KgRowEntity> kgBatch = new HashSet<>();
        Set<ObRowEntity> obBatch = new HashSet<>();
        Set<OrRowEntity> orBatch = new HashSet<>();
        Set<PlRowEntity> plBatch = new HashSet<>();
        Set<SbRowEntity> sbBatch = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile(), Charset.forName("Cp850")))) {
            var stopwatch = Stopwatch.createStarted();
            long duration;
            String line;
            while (null != (line = br.readLine())) {
                if (!line.isEmpty())
                    persist(line, kgBatch, obBatch, orBatch, plBatch, sbBatch);
            }
            if (!kgBatch.isEmpty()) kgRowRepository.saveAll(kgBatch);
            if (!obBatch.isEmpty()) obRowRepository.saveAll(obBatch);
            if (!orBatch.isEmpty()) orRowRepository.saveAll(orBatch);
            if (!plBatch.isEmpty()) plRowRepository.saveAll(plBatch);
            if (!sbBatch.isEmpty()) sbRowRepository.saveAll(sbBatch);

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
