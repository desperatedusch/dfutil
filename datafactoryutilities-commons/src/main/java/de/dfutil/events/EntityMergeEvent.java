package de.dfutil.events;

import de.dfutil.entities.RowType;

public final class EntityMergeEvent {

    private final RowType rowType;

    private final String row;

    public EntityMergeEvent(String row) {
        this.row = row;
        this.rowType = RowType.fromPrefix(row.substring(0, 2));
    }

    public RowType rowType() {
        return rowType;
    }

    public String row() {
        return row;
    }
}
