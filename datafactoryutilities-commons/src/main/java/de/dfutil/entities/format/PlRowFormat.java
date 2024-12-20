package de.dfutil.entities.format;

import java.util.List;

public enum PlRowFormat implements RowFormat<PlRowFormat> {

    PL01a("VERSION￾DATEIKENNUNG", 2, 1, 2),
    PL01b("VERSION-DATUM", 4, 3, 6),
    PL01c("VERSION-NR", 3, 7, 9),
    PL02("PLZ-DATUM", 8, 10, 17, true),
    PL03("PLZ-PLZ", 5, 18, 22, true),
    PL04("PLZ-ALORT", 8, 23, 30, true),
    PL05a("PLZ-ART￾KARDINALITÄT", 1, 31, 31, true),
    PL05b("PLZ-ART￾AUSLIEFERUNG", 1, 32, 32, true),
    PL06("PLZ-STVERZ", 1, 33, 33, true),
    PL07("PLZ-PFVERZ", 1, 34, 34, true),
    PL08("PLZ-ONAME", 40, 35, 74, true),
    PL09("PLZ-OZUSATZ", 30, 75, 104, true),
    PL10("PLZ-ART-OZUSATZ", 1, 105, 105, true),
    PL11("PLZ-ONAME24", 24, 106, 129, true),
    PL12("PLZ-POSTLAG", 1, 130, 130, true),
    PL13("PLZ-LA-BRIEF", 8, 131, 138, true),
    PL14("PLZ-LA-ALORT", 8, 139, 146, true),
    PL15("PLZ-KGS", 8, 147, 154, true),
    PL16("PLZ-ORT-CODE", 3, 155, 157, true),
    PL17("PLZ-LEITCODE-MAX", 3, 158, 160, true),
    PL18a("PLZ-RABATT-INFO￾SCHWER", 1, 161, 161, true),
    PL18b("PLZ-RESERVE", 2, 162, 163, true),
    PL19("PLZ-FZ-NR", 2, 164, 165, true),
    PL20("PLZ-BZ-NR", 2, 166, 167, true),
    PL21("SATZENDE", 1, 168, 168);


    private final String paramName;
    private final int fieldLength;
    private final int startingPos;
    private final int endingPos;
    private final boolean parseableProperty;

    private final RowType type = RowType.SB;

    private PlRowFormat(String desc, int length, int start, int end) {
        this.paramName = desc;
        this.fieldLength = length;
        this.startingPos = start;
        this.endingPos = end;
        this.parseableProperty = false;
    }

    private PlRowFormat(String desc, int length, int start, int end, boolean parseableProperty) {
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
    public List<PlRowFormat> paramList() {
        return List.of(PlRowFormat.values());
    }
}

