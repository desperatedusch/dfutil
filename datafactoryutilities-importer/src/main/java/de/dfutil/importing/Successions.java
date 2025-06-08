package de.dfutil.importing;

import com.google.common.base.Stopwatch;
import de.dfutil.importing.updates.MergeHandling;
import de.dfutil.importing.updates.OrphaneHandling;
import de.dfutil.importing.updates.ReplacementHandling;
import de.dfutil.importing.updates.SplitHandling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Successions {

    private static final Logger log = LoggerFactory.getLogger(Successions.class);

    private final OrphaneHandling orphaneHandling;
    private final ReplacementHandling replacementHandling;
    private final SplitHandling splitHandling;
    private final MergeHandling mergeHandling;

    public Successions(OrphaneHandling orphaneHandling, ReplacementHandling replacementHandling, SplitHandling splitHandling, MergeHandling mergeHandling) {
        this.orphaneHandling = orphaneHandling;
        this.replacementHandling = replacementHandling;
        this.splitHandling = splitHandling;
        this.mergeHandling = mergeHandling;
    }

    public void process() {
        var stopwatch = Stopwatch.createStarted();
        orphaneHandling.process();
        splitHandling.process();
        replacementHandling.process();
        mergeHandling.process();
        log.info("Finished successionhandling within {} ms", stopwatch.stop().elapsed().toMillis());
    }

}
