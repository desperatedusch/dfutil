package de.dfutil.entities.format;

import java.util.List;

public interface RowFormat<RF, RT> {

    public String getDescription();

    public int getFieldLength();

    public int getStartingPos();

    public int getEndingPos();

    public List<RF> paramValuesList();

    public void collectParamValue(RF cursorElement, RT sbRow);

}
