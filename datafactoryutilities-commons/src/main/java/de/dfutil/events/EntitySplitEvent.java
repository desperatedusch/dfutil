package de.dfutil.events;

import de.dfutil.entities.ArchivablePostalObject;

public class EntitySplitEvent {

    private ArchivablePostalObject archivablePostalObject;

    public EntitySplitEvent(ArchivablePostalObject archivablePostalObject) {
        this.archivablePostalObject = archivablePostalObject;
    }

    public ArchivablePostalObject getArchivablePostalObject() {
        return archivablePostalObject;
    }
}
