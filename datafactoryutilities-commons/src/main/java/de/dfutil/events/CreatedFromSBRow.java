package de.dfutil.events;

import de.dfutil.entities.SBRow;

public class CreatedFromSBRow extends CreatedFrom {

    public CreatedFromSBRow(Object source) {
        super(source);
        if (!(source instanceof SBRow))
            throw new RuntimeException("Source ist keine SBRow");
    }
}
