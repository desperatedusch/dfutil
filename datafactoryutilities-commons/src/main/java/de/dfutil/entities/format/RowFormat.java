package de.dfutil.entities.format;

import java.util.List;

public interface RowFormat<RF, RT> {

    public String getParamName();

    public int getFieldLength();

    public int getStartingPos();

    public int getEndingPos();

    public List<RF> paramValuesList();

    public RT collectedParamValue(RF cursorElement, RT rt);

}
