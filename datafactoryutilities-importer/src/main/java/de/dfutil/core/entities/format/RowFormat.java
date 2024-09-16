package de.dfutil.core.entities.format;

import java.util.List;

public interface RowFormat<RT> {

    public String getDescription();

    public int getFieldLength();

    public int getStartingPos();

    public int getEndingPos();

    public List<RT> paramValuesList();

}
