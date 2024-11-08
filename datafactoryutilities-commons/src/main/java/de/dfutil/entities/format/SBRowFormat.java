package de.dfutil.entities.format;

import java.util.List;

public enum SBRowFormat implements RowFormat<SBRowFormat> {

    ST01a("VERSION-DATEIKENNUNG", 2, 1, 2),
    ST01b("VERSION-DATUM", 4, 3, 6),
    ST01c("VERSION-NR", 3, 7, 9),
    ST02("STR-DATUM", 8, 10, 17, true),
    ST03("STR-ALORT", 8, 18, 25, true),
    ST04a("STR-NAMEN-SCHL", 6, 26, 31, true),
    ST04b("STR-BUND-LFDNR", 5, 32, 36, true),
    ST05("STR-HNRVON", 8, 37, 44, true),
    ST06("STR-HNRBIS", 8, 45, 52, true),
    ST07("STR-STATUS", 1, 53, 53, true),
    ST08("STR-HNR-1000", 1, 54, 54, true),
    ST09("STR-STVERZ", 1, 55, 55, true),
    ST10("STR-NAME-SORT", 46, 56, 101, true),
    ST11("STR-NAME46", 46, 102, 147, true),
    ST12("STR-NAME22", 22, 148, 169, true),
    ST13("STR-RESERVE", 1, 170, 170, true),
    ST14("STR-HNR-TYP", 1, 171, 171, true),
    ST15("STR-PLZ", 5, 172, 176, true),
    ST16("STR-CODE", 3, 177, 179, true),
    ST17("STR-OTL-SCHL", 3, 180, 182, true),
    ST18("STR-ALORG-B", 8, 183, 190, true),
    ST19("STR-KGS", 8, 191, 198, true),
    ST20("STR-ALORT-NEU", 8, 199, 206, true),
    ST21a("STR-NAMEN-SCHL-NEU", 6, 207, 212, true),
    ST21b("STR-BUND-LFDNR-NEU", 5, 213, 217, true),
    ST22("STR-HNRVON-NEU", 8, 218, 225, true),
    ST23("STR-HNRBIS-NEU", 8, 226, 233, true),
    ST24("SATZENDE", 1, 234, 234);

    private final String paramName;
    private final int fieldLength;
    private final int startingPos;
    private final int endingPos;
    private final boolean parseableProperty;

    private final RowType type = RowType.SB;

    private SBRowFormat(String desc, int length, int start, int end) {
        this.paramName = desc;
        this.fieldLength = length;
        this.startingPos = start;
        this.endingPos = end;
        this.parseableProperty = false;
    }

    private SBRowFormat(String desc, int length, int start, int end, boolean parseableProperty) {
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
    public List<SBRowFormat> paramList() {
        return List.of(SBRowFormat.values());
    }
}

