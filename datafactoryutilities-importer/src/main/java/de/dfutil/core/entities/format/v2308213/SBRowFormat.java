package de.dfutil.core.entities.format.v2308213;

import de.dfutil.core.entities.format.RowFormat;

import java.util.List;

public enum SBRowFormat implements RowFormat {

    ST01a("VERSION-DATEIKENNUNG", 2, 1, 2),
    ST01b("VERSION-DATUM", 4, 3, 6),
    ST01c("VERSION-NR", 3, 7, 9),
    ST02("STR-DATUM", 8, 10, 17),
    ST03("STR-ALORT", 8, 18, 25),
    ST04a("STR-NAMEN-SCHL", 6, 26, 31),
    ST04b("STR-BUND-LFDNR", 5, 32, 36),
    ST05("STR-HNRVON", 8, 37, 44),
    ST06("STR-HNRBIS", 8, 45, 52),
    ST07("STR-STATUS", 1, 53, 53),
    ST08("STR-HNR-1000", 1, 54, 54),
    ST09("STR-STVERZ", 1, 55, 55),
    ST10("STR-NAME-SORT", 46, 56, 101),
    ST11("STR-NAME46", 46, 102, 147),
    ST12("STR-NAME22", 22, 148, 169),
    ST13("STR-RESERVE", 1, 170, 170),
    ST14("STR-HNR-TYP", 1, 171, 171),
    ST15("STR-PLZ", 5, 172, 176),
    ST16("STR-CODE", 3, 177, 179),
    ST17("STR-OTL-SCHL", 3, 180, 182),
    ST18("STR-ALORG-B", 8, 183, 190),
    ST19("STR-KGS", 8, 191, 198),
    ST20("STR-ALORT-NEU", 8, 199, 206),
    ST21a("STR-NAMEN-SCHL-NEU", 6, 207, 212),
    ST21b("STR-BUND-LFDNR-NEU", 5, 213, 217),
    ST22("STR-HNRVON-NEU", 8, 218, 225),
    ST23("STR-HNRBIS-NEU", 8, 226, 233),
    ST24("SATZENDE", 1, 234, 234);

    private final String description;
    private final int fieldLength;
    private final int startingPos;
    private final int endingPos;

    private final RowType type = RowType.SB;

    private SBRowFormat(String desc, int length, int start, int end) {
        this.description = desc;
        this.fieldLength = length;
        this.startingPos = start;
        this.endingPos = end;
    }

    public String getDescription() {
        return description;
    }

    public int getFieldLength() {
        return fieldLength;
    }

    public int getStartingPos() {
        return startingPos;
    }

    public int getEndingPos() {
        return endingPos;
    }

    @Override
    public List paramValuesList() {
        return List.of(SBRowFormat.values());
    }


}

