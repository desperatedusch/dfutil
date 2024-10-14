package de.dfutil.events;

import de.dfutil.entities.SBRow;

public class CreatedFromSBRow extends CreatedFrom {

    private final SBRow content;

    public CreatedFromSBRow(Object source) {
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
