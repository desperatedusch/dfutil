package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import de.dfutil.importing.updates.Merges;
import de.dfutil.importing.updates.Orphanes;
import de.dfutil.importing.updates.Replacements;
import de.dfutil.importing.updates.Splittings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RelationshipUpdating {

    private static final Logger log = LoggerFactory.getLogger(RelationshipUpdating.class);

    private final ImporterConfigurationProperties importerConfigurationProperties;
    private final Orphanes orphanes;
    private final Replacements replacements;
    private final Splittings splittings;
    private final Merges merges;

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;


    public RelationshipUpdating(ImporterConfigurationProperties importerConfigurationProperties, Orphanes orphanes, Replacements replacements, Splittings splittings, Merges merges, OrRowRepository orRowRepository, ObRowRepository obRowRepository, SbRowRepository sbRowRepository) {
        this.importerConfigurationProperties = importerConfigurationProperties;
        this.orphanes = orphanes;
        this.replacements = replacements;
        this.splittings = splittings;
        this.merges = merges;
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    public void process() {
        var stopwatch = Stopwatch.createStarted();
        if (importerConfigurationProperties
                .isResetSuccessionHandlingApplicationStateActivated()) {
            resetSuccessionsApplicationState();
        }
        orphanes.process();
        splittings.process();
        replacements.process();
        merges.process();
        log.info("Finished successionhandling within {} ms", stopwatch.stop().elapsed().toMillis());
    }

    private void resetSuccessionsApplicationState() {
        Lists.newArrayList(orRowRepository, obRowRepository, sbRowRepository).forEach(
                sar -> {
                    sar.resetAppliedState();
                    sar.resetOutdatedState();
                });
    }

}
