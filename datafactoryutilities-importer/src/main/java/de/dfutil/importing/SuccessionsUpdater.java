package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import de.dfutil.importing.updates.MergesRelationalDatabaseHandling;
import de.dfutil.importing.updates.OrphanesRelationalDatabaseHandling;
import de.dfutil.importing.updates.ReplacementsRelationalDatabaseHandling;
import de.dfutil.importing.updates.SplittingsRelationalDatabaseHandling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SuccessionsUpdater {

    private static final Logger log = LoggerFactory.getLogger(SuccessionsUpdater.class);

    private final OrphanesRelationalDatabaseHandling orphanes;
    private final ReplacementsRelationalDatabaseHandling replacements;
    private final SplittingsRelationalDatabaseHandling splittings;
    private final MergesRelationalDatabaseHandling merges;

    public SuccessionsUpdater(OrphanesRelationalDatabaseHandling orphanes, ReplacementsRelationalDatabaseHandling replacements, SplittingsRelationalDatabaseHandling splittings, MergesRelationalDatabaseHandling merges) {
        this.orphanes = orphanes;
        this.replacements = replacements;
        this.splittings = splittings;
        this.merges = merges;
    }

    public void process() {
        var stopwatch = Stopwatch.createStarted();
        orphanes.process();
        splittings.process();
        replacements.process();
        merges.process();
        log.info("Finished successionhandling within {} ms", stopwatch.stop().elapsed().toMillis());
    }

}
