package de.dfutil.events;

import org.springframework.context.ApplicationEvent;

public class EntityMergeEvent extends ApplicationEvent {

    public EntityMergeEvent(Object source) {
        super(source);
    }
}
