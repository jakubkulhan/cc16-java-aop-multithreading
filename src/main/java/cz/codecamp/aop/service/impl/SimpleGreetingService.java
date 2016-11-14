package cz.codecamp.aop.service.impl;

import cz.codecamp.aop.service.GreetingServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class SimpleGreetingService implements GreetingServiceInterface {
    @Override
    public String makeGreeting(String name) {
        return String.format("Hello, %s!", name);
    }
}
