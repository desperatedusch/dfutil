package de.dfutil.core.listener;

import de.dfutil.events.RowParsedEvent;

public interface DataImportHandler {

    void persistEventContent2DataSources(RowParsedEvent event);
}
