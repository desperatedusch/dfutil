package de.dfutil.entities;

public enum ArchivingState {

    G("gültiger Satz", "VALID"),
    S("archivierter Satz(Schlüsseländerung) Verweis auf eindeutigen neuen Satz", "SINGLE_SUCCESSOR"),
    N("archivierter Satz (Schlüsseländerung) mit Verweis auf mehrere neue Sätze dabei steht N für 1, 2...", "MULTIPLE_SUCCESSORS"),
    W("ersatzlos weggefallener Satz", "INVALID");

    private final String description;
    private final String name;

    ArchivingState(String s, String name) {
        this.description = s;
        this.name = name;
    }

    public String description() {
        return description;
    }

    public String getName() {
        return name;
    }
}
