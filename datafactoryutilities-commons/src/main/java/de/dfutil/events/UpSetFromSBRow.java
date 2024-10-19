package de.dfutil.events;

import de.dfutil.entities.SBRow;
import org.springframework.context.ApplicationEvent;

public class UpSetFromSBRow extends ApplicationEvent {

    private final SBRow content;

    public UpSetFromSBRow(Object source) {
        super(source);
        if (!(source instanceof SBRow))
            throw new RuntimeException("Content no SBRow type object");
        else
            this.content = (SBRow) source;
    }

    public SBRow getContent() {
        return content;
    }
}
