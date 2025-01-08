package de.dfutil.entities;

public enum ArchivingState {

    G("gültiger Satz"),
    S("archivierter Satz(Schlüsseländerung) Verweis auf eindeutigen neuen Satz"),
    N("archivierter Satz (Schlüsseländerung) mit Verweis auf mehrere neue Sätze dabei steht N für 1, 2..."),
    W("ersatzlos weggefallener Satz");

    private final String description;

    ArchivingState(String s) {
        this.description = s;
    }

    public String description() {
        return description;
    }
}
