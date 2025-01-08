package de.dfutil.events;

import org.springframework.context.ApplicationEvent;

public class EntitySplitEvent extends ApplicationEvent {

    public EntitySplitEvent(Object source) {
        super(source);
    }
}
