package com.example.studentregistration.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHolderListener {

    @EventListener
    public void listen(EventHolder eventHolder) {
        System.out.print(eventHolder.getEventAction().getValue());
        System.out.println(eventHolder.getEventObject());
    }
}
