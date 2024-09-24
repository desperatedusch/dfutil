package de.dfutil.events;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class MergeWith extends ApplicationEvent {
    public MergeWith(Object source) {
        super(source);
    }

    public MergeWith(Object source, Clock clock) {
        super(source, clock);
    }
}
