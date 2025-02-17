package de.dfutil.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ArchivingInformation implements Serializable {

    private ArchivingObjectType archivingObjectType;
    private ArchivingState archivingState;

    private String datum;
    private String schluessel;
    private String status;
    private String schluesselNeu;

    private ArchivablePostalObject predecessor;

    public ArchivingInformation(
            ArchivingObjectType archivingObjectType,
            ArchivingState archivingState,
            String datum,
            String schluessel,
            String status,
            String schluesselNeu) {
        this.archivingObjectType = archivingObjectType;
        this.archivingState = archivingState;
        this.datum = datum;
        this.schluessel = schluessel;
        this.status = status;
        this.schluesselNeu = schluesselNeu;
    }

    public ArchivingInformation() {
    }

    public ArchivingObjectType archivingObjectType() {
        return archivingObjectType;
    }

    public void setArchivingObjectType(ArchivingObjectType archivingObjectType) {
        this.archivingObjectType = archivingObjectType;
    }

    public ArchivingState archivingState() {
        return archivingState;
    }

    public void setArchivingState(ArchivingState archivingState) {
        this.archivingState = archivingState;
    }

    public String datum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String schluessel() {
        return schluessel;
    }

    public void setSchluessel(String schluessel) {
        this.schluessel = schluessel;
    }

    public String status() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String schluesselNeu() {
        return schluesselNeu;
    }

    public void setSchluesselNeu(String schluesselNeu) {
        this.schluesselNeu = schluesselNeu;
    }
}
