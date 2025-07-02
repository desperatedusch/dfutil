package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import de.dfutil.importing.updates.MergesHandling;
import de.dfutil.importing.updates.OrphanesHandling;
import de.dfutil.importing.updates.ReplacementsHandling;
import de.dfutil.importing.updates.SplittingsHandling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SuccessionsUpdater {

    private static final Logger log = LoggerFactory.getLogger(SuccessionsUpdater.class);

    private final OrphanesHandling orphanes;
    private final ReplacementsHandling replacements;
    private final SplittingsHandling splittings;
    private final MergesHandling merges;

    public SuccessionsUpdater(OrphanesHandling orphanes, ReplacementsHandling replacements, SplittingsHandling splittings, MergesHandling merges) {
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
