package de.dfutil.core.handler;

import de.dfutil.events.RowParsedEvent;

public interface DataImportHandler {

    void persistEventContent2DataSources(RowParsedEvent event);
}
