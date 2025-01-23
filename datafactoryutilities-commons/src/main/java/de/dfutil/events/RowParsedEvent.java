package de.dfutil.events;

import de.dfutil.entities.RowType;

public final class RowParsedEvent {

    private final RowType rowType;
    private final String row;

    public RowParsedEvent(String row) {
        this.rowType = RowType.fromPrefix(row.substring(0, 2));
        this.row = row;
    }

    public RowType rowType() {
        return rowType;
    }

    public String row() {
        return row;
    }

}
