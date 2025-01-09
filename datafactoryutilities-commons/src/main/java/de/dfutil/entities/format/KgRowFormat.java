package de.dfutil.entities.format;

import java.util.List;

public enum KgRowFormat implements RowFormat<KgRowFormat> {

    KG01a("VERSION￾DATEIKENNUNG", 2, 1, 2),
    KG01b("VERSION-DATUM", 4, 3, 6),
    KG01c("VERSION-NR", 3, 7, 9),
    KG02("KG-DATUM", 8, 10, 17, true),
    KG03("KG-SCHLUESSEL", 8, 18, 25, true),
    KG04("KG-SATZART", 1, 26, 26, true),
    KG05("KG-Name", 40, 27, 66, true),
    KG06("SATZENDE", 1, 67, 67);

    private final String paramName;
    private final int fieldLength;
    private final int startingPos;
    private final int endingPos;
    private final boolean parseableProperty;

    private final RowType type = RowType.KG;

    private KgRowFormat(String desc, int length, int start, int end) {
        this.paramName = desc;
        this.fieldLength = length;
        this.startingPos = start;
        this.endingPos = end;
        this.parseableProperty = false;
    }

    private KgRowFormat(String desc, int length, int start, int end, boolean parseableProperty) {
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
    public List<KgRowFormat> paramList() {
        return List.of(KgRowFormat.values());
    }

}

