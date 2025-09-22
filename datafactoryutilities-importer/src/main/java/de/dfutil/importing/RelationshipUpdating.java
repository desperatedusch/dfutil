package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import de.dfutil.importing.updates.Orphanes;
import de.dfutil.importing.updates.Replacements;
import de.dfutil.importing.updates.SplittingsAndMerges;
import de.dfutil.importing.updates.SuccessionStateReset;
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

    private final Orphanes orphanes;
    private final Replacements replacements;
    private final SplittingsAndMerges splittingsAndMerges;
    private final SuccessionStateReset successionStateReset;

    public RelationshipUpdating(
            Orphanes orphanes,
            Replacements replacements,
            SplittingsAndMerges splittingsAndMerges,
            SuccessionStateReset successionStateReset
    ) {
        this.orphanes = orphanes;
        this.replacements = replacements;
        this.splittingsAndMerges = splittingsAndMerges;
        this.successionStateReset = successionStateReset;
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
        successionStateReset.process();
    }

}
