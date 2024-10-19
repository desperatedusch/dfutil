package de.dfutil.core;

import de.dfutil.core.listener.SBHandler;
import de.dfutil.events.UpSetFromSBRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class Importer{

    private static final Logger log = LoggerFactory.getLogger(Importer.class);

    @Autowired
    private SBHandler sbHandler;


    public Importer() {

    }

    @EventListener(UpSetFromSBRow.class)
    public void onApplicationEvent(UpSetFromSBRow event) {
        log.trace("Event {} received", event);
        sbHandler.updateOrInsert(event.getContent());
    }



}
