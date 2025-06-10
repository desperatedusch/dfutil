package de.dfutil.entities;

public enum ArchivingState {
    VALID("gültiger Satz", "G"),
    SINGLE_SUCCESSOR("archivierter Satz(Schlüsseländerung) Verweis auf eindeutigen neuen Satz", "S"),
    MULTIPLE_SUCCESSORS("archivierter Satz (Schlüsseländerung) mit Verweis auf mehrere neue Sätze dabei steht N für 1, 2...", "N"),
    INVALID("ersatzlos weggefallener Satz", "W");

    private final String description;
    private final String symbol;

    ArchivingState(String s, String symbol) {
        this.description = s;
        this.symbol = symbol;
    }

    public String description() {
        return description;
    }

    public String symbol() {
        return symbol;
    }


}
