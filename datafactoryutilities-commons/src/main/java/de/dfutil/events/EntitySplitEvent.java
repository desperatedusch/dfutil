package de.dfutil.events;

import de.dfutil.entities.RowType;
import org.springframework.context.ApplicationEvent;

public class EntitySplitEvent extends ApplicationEvent {

    private final RowType rowType;
    private final byte[] row;

    public EntitySplitEvent(Object source) {
        super(source);
        this.rowType = RowType.fromPrefix(((String) source).substring(0, 2));
        this.row = String.valueOf(source).getBytes();
    }

    public RowType rowType() {
        return rowType;
    }

    public byte[] row() {
        return row;
    }

}
