package de.dfutil.entities.format;

import java.util.List;

public enum OrRowFormat implements RowFormat<OrRowFormat> {

    OR01a("VERSION-DATEIKENNUNG", 2, 1, 2),
    OR01b("VERSION-DATUM", 4, 3, 6),
    OR01c("VERSION-NR", 3, 7, 9),
    OR02("ORT-DATUM", 8, 10, 17, true),
    OR03("ORT-ALORT", 8, 18, 25, true),
    OR04("ORT-STATUS", 1, 26, 26, true),
    OR05("ORT-ONAME", 40, 27, 66, true),
    OR06("ORT-ONAME-POST", 40, 67, 106, true),
    OR07("ORT-OZUSATZ", 30, 107, 136, true),
    OR08("ORT-ART-OZUSATZ", 1, 137, 137, true),
    OR09("ORT-ONAME24", 24, 138, 161, true),
    OR10("ORT-KGS", 8, 162, 169, true),
    OR11("ORT-ALORT-NEU", 8, 170, 177, true),
    OR12("SATZENDE", 1, 178, 178);


    private final String paramName;
    private final int fieldLength;
    private final int startingPos;
    private final int endingPos;
    private final boolean parseableProperty;

    private final RowType type = RowType.OR;


    private OrRowFormat(String desc, int length, int start, int end) {
        this.paramName = desc;
        this.fieldLength = length;
        this.startingPos = start;
        this.endingPos = end;
        this.parseableProperty = false;
    }

    private OrRowFormat(String desc, int length, int start, int end, boolean parseableProperty) {
        this.paramName = desc;
        this.fieldLength = length;
        this.startingPos = start;
        this.endingPos = end;
        this.parseableProperty = parseableProperty;
    }

    public String paramName() {
        return paramName;
    }

    public int fieldLength() {
        return fieldLength;
    }

    public int startingPos() {
        return startingPos;
    }

    public int endingPos() {
        return endingPos;
    }

    public boolean parseableContent() {
        return parseableProperty;
    }

    @Override
    public List<OrRowFormat> paramList() {
        return List.of(OrRowFormat.values());
    }
    }

