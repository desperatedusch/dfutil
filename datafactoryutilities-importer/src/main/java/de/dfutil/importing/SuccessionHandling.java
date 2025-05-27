package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SuccessionHandling {

    private static final Logger log = LoggerFactory.getLogger(SuccessionHandling.class);

    private final Orphanes orphanes;
    private final Replacements replacements;
    private final Splittings splittings;
    private final Merges merges;

    public SuccessionHandling(Orphanes orphanes, Replacements replacements, Splittings splittings, Merges merges) {
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
