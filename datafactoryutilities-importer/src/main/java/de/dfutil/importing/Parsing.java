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

    public Parsing(final ImporterConfigurationProperties importerConfigurationProperties, final SbRowRepository sbRowRepository, final PlRowRepository plRowRepository, final OrRowRepository orRowRepository, final ObRowRepository obRowRepository, final KgRowRepository kgRowRepository, final Postprocessing postprocessing) {
        this.importerConfigurationProperties = importerConfigurationProperties;
        this.sbRowRepository = sbRowRepository;
        this.plRowRepository = plRowRepository;
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.kgRowRepository = kgRowRepository;
        this.postprocessing = postprocessing;
    }

    private void persist(final String line, final Set<KgRowEntity> kgBatch, final Set<ObRowEntity> obBatch,
                         final Set<OrRowEntity> orBatch, final Set<PlRowEntity> plBatch, final Set<SbRowEntity> sbBatch) {
        final String prefix = line.substring(0, 2);
        switch (prefix) {
            case "KG":
                final KgRowEntity kg = KgRowEntity.parseFrom(line);
                if (!this.kgRowRepository.existsById(kg.getKgRowId())) {
                    kgBatch.add(kg);
                    this.rowsProcessed.incrementAndGet();
                }
                break;
            case "OB":
                final ObRowEntity ob = ObRowEntity.parseFrom(line);
                if (!this.obRowRepository.existsById(ob.getObRowId())) {
                    obBatch.add(ob);
                    this.rowsProcessed.incrementAndGet();
                }
                break;
            case "OR":
                final OrRowEntity or = OrRowEntity.parseFrom(line);
                if (!this.orRowRepository.existsById(or.getOrRowId())) {
                    orBatch.add(or);
                    this.rowsProcessed.incrementAndGet();
                }
                break;
            case "PL":
                final PlRowEntity pl = PlRowEntity.parseFrom(line);
                if (!this.plRowRepository.existsById(pl.getPlRowId())) {
                    plBatch.add(pl);
                    this.rowsProcessed.incrementAndGet();
                }
                break;
            case "SB":
                final SbRowEntity sb = SbRowEntity.parseFrom(line);
                if (!this.sbRowRepository.existsById(sb.getSbRowId())) {
                    sbBatch.add(sb);
                    this.rowsProcessed.incrementAndGet();
                }
                break;
            default:
                break;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void fromFile(final Path path) {
        this.rowsProcessed.set(0);
        final Set<KgRowEntity> kgBatch = new HashSet<>();
        final Set<ObRowEntity> obBatch = new HashSet<>();
        final Set<OrRowEntity> orBatch = new HashSet<>();
        final Set<PlRowEntity> plBatch = new HashSet<>();
        final Set<SbRowEntity> sbBatch = new HashSet<>();

        try (final BufferedReader br = new BufferedReader(new FileReader(path.toFile(), Charset.forName("Cp850")))) {
            final var BATCH_SIZE = this.importerConfigurationProperties.getBatchSize();
            final var stopwatch = Stopwatch.createStarted();
            final long duration;
            String line;
            while (null != (line = br.readLine())) {
                if (!line.isEmpty()) {
                    this.persist(line, kgBatch, obBatch, orBatch, plBatch, sbBatch);
                    if (kgBatch.size() >= BATCH_SIZE) {
                        this.kgRowRepository.saveAll(kgBatch);
                        kgBatch.clear();
                    }
                    if (obBatch.size() >= BATCH_SIZE) {
                        this.obRowRepository.saveAll(obBatch);
                        obBatch.clear();
                    }
                    if (orBatch.size() >= BATCH_SIZE) {
                        this.orRowRepository.saveAll(orBatch);
                        orBatch.clear();
                    }
                    if (plBatch.size() >= BATCH_SIZE) {
                        this.plRowRepository.saveAll(plBatch);
                        plBatch.clear();
                    }
                    if (sbBatch.size() >= BATCH_SIZE) {
                        this.sbRowRepository.saveAll(sbBatch);
                        sbBatch.clear();
                    }
                }
            }
            if (!kgBatch.isEmpty()) this.kgRowRepository.saveAll(kgBatch);
            if (!obBatch.isEmpty()) this.obRowRepository.saveAll(obBatch);
            if (!orBatch.isEmpty()) this.orRowRepository.saveAll(orBatch);
            if (!plBatch.isEmpty()) this.plRowRepository.saveAll(plBatch);
            if (!sbBatch.isEmpty()) this.sbRowRepository.saveAll(sbBatch);

            duration = stopwatch.stop().elapsed().toMillis();
            Parsing.log.info("Successfully parsed, imported {} rows with new/updated content, from {} within {} ms", this.rowsProcessed, path, duration);

            this.postprocessing.parsedSuccessfully(path, duration);
            this.postprocessing.deleteProcessedInputSources(path);
        } catch (final IOException e) {
            Parsing.log.error("Parsing {} failed\n{}", path, e.getMessage());
            this.postprocessing.parsingFailed(path, null);
            throw new RuntimeException(e);
        }
    }

}
