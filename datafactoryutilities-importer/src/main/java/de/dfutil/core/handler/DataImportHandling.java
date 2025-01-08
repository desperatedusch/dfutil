package de.dfutil.core.handler;

import de.dfutil.events.RowParsedEvent;

public interface DataImportHandling {

    void persistEventContent(RowParsedEvent event);
}
