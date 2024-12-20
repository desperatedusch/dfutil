package de.dfutil.entities.format;

import java.util.List;

public enum OrRowFormat implements RowFormat<OrRowFormat> {

    OR01a("VERSIONDATEIKENNUNG", 2, 1, 2),
    OR01b("VERSION-DATUM", 4, 3, 6),
    OR01c VERSION-NR 03 007 009
        private final String paramName;-DATUM 008 010 017
        private final int fieldLength;-ALORT 008 018 025
        private final int startingPos;-STATUS 001 026 026
        private final int endingPos;-ONAME 040 027 066
        private final boolean parseableProperty;-ONAME-POST 040 067 106
        private final RowType type = RowType.OR;-OZUSATZ 030 107 136
    OR02 ORT-ART-OZUSATZ 001 137 137
    OR03 ORT-ONAME24 024 138 161
    OR04 ORT-KGS 008 162 169
    OR05 ORT-ALORT-NEU 008 170 177
    OR06 ORT 001 178 178
OR07 ORT
OR08 ORT
OR09 ORT
OR10 ORT
OR11 ORT
OR12 SATZENDE

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

