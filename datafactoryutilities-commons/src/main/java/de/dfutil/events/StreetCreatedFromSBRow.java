package de.dfutil.events;

import de.dfutil.entities.Street;
import org.springframework.context.ApplicationEvent;

public class StreetCreatedFromSBRow extends ApplicationEvent {

    public StreetCreatedFromSBRow(Object source) {
        super(source);
        if (!(source instanceof Street))
            throw new RuntimeException("Source ist keine Strasse");
    }
}
