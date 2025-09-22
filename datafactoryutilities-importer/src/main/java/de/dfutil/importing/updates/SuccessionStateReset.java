package de.dfutil.importing.updates;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import de.dfutil.dao.ObRowRepository;
import de.dfutil.dao.OrRowRepository;
import de.dfutil.dao.SbRowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SuccessionStateReset {

    private static final Logger log = LoggerFactory.getLogger(SuccessionStateReset.class);

    private final OrRowRepository orRowRepository;
    private final ObRowRepository obRowRepository;
    private final SbRowRepository sbRowRepository;

    public SuccessionStateReset(OrRowRepository orRowRepository,
                                ObRowRepository obRowRepository,
                                SbRowRepository sbRowRepository) {
        this.orRowRepository = orRowRepository;
        this.obRowRepository = obRowRepository;
        this.sbRowRepository = sbRowRepository;
    }

    public void process() {
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
