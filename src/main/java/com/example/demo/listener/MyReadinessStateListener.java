package com.example.demo.listener;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.spring-application.application-availability.managing
 * https://www.baeldung.com/spring-liveness-readiness-probes
 */
@Component
public class MyReadinessStateListener
{
    @EventListener
    public void onStateChanged(AvailabilityChangeEvent<ReadinessState> event)
    {
        System.out.println("App readiness state = " + event.getState());
    }
}
