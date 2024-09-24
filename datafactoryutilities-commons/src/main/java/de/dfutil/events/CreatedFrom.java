package de.dfutil.events;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class CreatedFrom extends ApplicationEvent {
    public CreatedFrom(Object source) {
        super(source);
    }

    public CreatedFrom(Object source, Clock clock) {
        super(source, clock);
    }
}
