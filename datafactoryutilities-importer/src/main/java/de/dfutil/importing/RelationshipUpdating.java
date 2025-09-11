package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import de.dfutil.importing.updates.Orphanes;
import de.dfutil.importing.updates.Replacements;
import de.dfutil.importing.updates.SplittingsAndMerges;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableConfigurationProperties(ImporterConfigurationProperties.class)
public class RelationshipUpdating {

    private static final Logger log = LoggerFactory.getLogger(RelationshipUpdating.class);

    private final ImporterConfigurationProperties importerConfigurationProperties;
    private final Orphanes orphanes;
    private final Replacements replacements;
    private final SplittingsAndMerges splittingsAndMerges;

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public RelationshipUpdating(
            ImporterConfigurationProperties importerConfigurationProperties,
            Orphanes orphanes,
            Replacements replacements,
            SplittingsAndMerges splittingsAndMerges,
            OrRowRepository orRowRepository,
            ObRowRepository obRowRepository,
            SbRowRepository sbRowRepository
    ) {
        this.importerConfigurationProperties = importerConfigurationProperties;
        this.orphanes = orphanes;
        this.replacements = replacements;
        this.splittingsAndMerges = splittingsAndMerges;
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    public void process() {
        var stopwatch = Stopwatch.createStarted();
        log.info("Starting successionhandling...");
        orphanes.process();
        splittingsAndMerges.process();
        replacements.process();
        log.info("Finished successionhandling within {} ms", stopwatch.stop().elapsed().toMillis());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void resetSuccessionsApplicationState() {
        var stopwatch = Stopwatch.createStarted();
        log.info("Starting Reset of SuccessionApplicationState...");
        Lists.newArrayList(orRowRepository, obRowRepository, sbRowRepository).forEach(
                sar -> {
                    sar.resetAppliedState();
                    sar.resetOutdatedState();
                });
        log.info("Finished Reset of SuccessionApplicationState within {} ms", stopwatch.stop().elapsed().toMillis());
    }

}
