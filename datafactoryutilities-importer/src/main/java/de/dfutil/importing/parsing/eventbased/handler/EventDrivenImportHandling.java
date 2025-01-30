package de.dfutil.importing.parsing.eventbased.handler;

import de.dfutil.events.RowParsedEvent;
import org.springframework.context.annotation.Profile;

@Profile({"eventbased-importing", "!procedural-importing "})
public interface EventDrivenImportHandling {

    void persistEventContent(RowParsedEvent event);

}
