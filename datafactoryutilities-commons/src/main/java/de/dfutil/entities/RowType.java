package de.dfutil.entities;

public enum RowType {
    GE("GROSS-DA", "Großempfängeradressen mit Hausadressen", 324),
    KG(
            "KGS-DA",
            "Bundesländer, Regierungsbezirke, Kreise, kreisfreie Städte mit 8-stelligem Schlüssel des statistischen Bundesamtes",
            67),
    OE("ORG-DA", "Organisationseinheiten der Deutschen Post mit Adressen und Funktionen", 272),
    OR("ORT-DA", "Orte und Archivorte", 178),
    OB("OTL-DB", "Ortsteiledatei", 84),
    PF("POFA-DA", "Postfach- und Schalterausgaben", 66),
    PL("PLZ-DA", "Postleitzahl, gültige Ortsnamen und Art der Postleitzahl", 160),
    SB("STRA-DB", "Straßen und Archivstraßen", 234),
    XX("PRUEF-DA", "Statistische Informationen über die jeweilige Version", 37);

    private final String dateiName;
    private final String dateiInhalt;
    private final int satzLaenge;

    RowType(String dateiName, String dateiInhalt, int satzLaenge) {
        this.dateiName = dateiName;
        this.dateiInhalt = dateiInhalt;
        this.satzLaenge = satzLaenge;
    }

    public static RowType fromPrefix(String prefix) {
        return switch (prefix) {
            case "OE" -> OE;
            case "GE" -> GE;
            case "KG" -> KG;
            case "OR" -> OR;
            case "OB" -> OB;
            case "PL" -> PL;
            case "PF" -> PF;
            case "SB" -> SB;
            case "XX" -> XX;
            default -> throw new IllegalArgumentException("Unkown prefix: " + prefix);
        };
    }

    public String dateiName() {
        return dateiName;
    }

    public String dateiInhalt() {
        return dateiInhalt;
    }

    public int satzLaenge() {
        return satzLaenge;
    }
}
