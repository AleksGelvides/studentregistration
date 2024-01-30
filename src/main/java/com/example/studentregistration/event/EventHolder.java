package com.example.studentregistration.event;

import com.example.studentregistration.enums.EventAction;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventHolder extends ApplicationEvent {

    private final EventAction eventAction;
    private final Object eventObject;
    public EventHolder(Object source, EventAction eventAction, Object eventObject) {
        super(source);
        this.eventAction = eventAction;
        this.eventObject = eventObject;
    }
}
