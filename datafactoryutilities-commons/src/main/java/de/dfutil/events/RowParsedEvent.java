package de.dfutil.events;

import de.dfutil.entities.format.RowType;
import org.springframework.context.ApplicationEvent;

public class RowParsedEvent extends ApplicationEvent {

    private final RowType rowType;

    public RowParsedEvent(Object source) {
        super(source);
        this.rowType = RowType.fromPrefix(((String) source).substring(0, 2));
    }

    public RowType rowType() {
        return rowType;
    }

}
