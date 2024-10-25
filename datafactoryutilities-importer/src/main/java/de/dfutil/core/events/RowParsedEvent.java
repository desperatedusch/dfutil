package de.dfutil.core.events;

import org.springframework.context.ApplicationEvent;

public class RowParsedEvent extends ApplicationEvent {

    private final String content;

    public RowParsedEvent(Object source) {
        super(source);
        throw new RuntimeException(new UnsupportedOperationException("No Non-content containing object creation of UpSet events allowed..."));
    }

    public RowParsedEvent(Object source, String content) {
        super(source);
        this.content = content;

    }

    public String content() {
        return content;
    }
}
