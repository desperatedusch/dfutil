package de.dfutil.entities;

public enum ArchivingState {
    VALID("gültiger Satz", "G"),
    SINGLE_SUCCESSOR("archivierter Satz(Schlüsseländerung) Verweis auf eindeutigen neuen Satz", "S"),
    MULTIPLE_SUCCESSORS("archivierter Satz (Schlüsseländerung) mit Verweis auf mehrere neue Sätze dabei steht N für 1, 2...", "N"),
    INVALID("ersatzlos weggefallener Satz", "W");

    private final String description;
    private final String symbol;

    ArchivingState(final String s, final String symbol) {
        description = s;
        this.symbol = symbol;
    }

    public String description() {
        return this.description;
    }

    public String symbol() {
        return this.symbol;
    }


}
