package de.dfutil.entities.format;

import java.util.List;

public interface RowFormat<RF> {

    public String paramName();

    public int fieldLength();

    public int startingPos();

    public int endingPos();

    public List<RF> paramList();
}
