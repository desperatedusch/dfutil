package de.dfutil.core.entities;

public enum HausnummernTyp {

    NICHT_GETEILT("N"),
    GERADE("G"),
    UNGERADE("U");

    private String typKennzeichen;

    private HausnummernTyp(String typKennzeichen) {
        this.typKennzeichen = typKennzeichen;
    }


}
