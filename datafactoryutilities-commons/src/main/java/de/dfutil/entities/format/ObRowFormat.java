package de.dfutil.entities.format;

import java.util.List;

public enum ObRowFormat implements RowFormat<ObRowFormat> {


    OB01a("VERSION￾DATEIKENNUNG", 2, 1, 2),
    OB01b("VERSION-DATUM", 4, 3, 6),
    OB01c("VERSION-NR", 3, 7, 9),
    OB02("OTL-DATUM", 8, 10, 17, true),
    OB03("OTL-ALORT", 8, 18, 25, true),
    OB04("OTL-SCHL", 3, 26, 28, true),
    OB05("OTL-PLZ", 5, 29, 33, true),
    OB06("OTL-STATUS", 1, 34, 34, true),
    OB07("OTL-STVERZ", 1, 35, 35, true),
    OB08("OTL-NAME", 40, 36, 75, true),
    OB09("OTL-KGS", 8, 76, 83, true),
    OB10("SATZENDE", 1, 84, 84);

    private final String paramName;
    private final int fieldLength;
    private final int startingPos;
    private final int endingPos;
    private final boolean parseableProperty;

    private final RowType type = RowType.OB;


    private ObRowFormat(String desc, int length, int start, int end) {
        this.paramName = desc;
        this.fieldLength = length;
        this.startingPos = start;
        this.endingPos = end;
        this.parseableProperty = false;
    }

    private ObRowFormat(String desc, int length, int start, int end, boolean parseableProperty) {
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
    public List<ObRowFormat> paramList() {
        return List.of(ObRowFormat.values());
    }
}

