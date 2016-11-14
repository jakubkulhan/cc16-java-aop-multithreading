package cz.codecamp.aop.service;

import cz.codecamp.aop.aspect.annotation.Auditable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    @Autowired
    GreetingServiceInterface greetingService;

    public void sayHelloWorld() {
        System.out.println(greetingService.makeGreeting("world"));
    }

    public void sayHelloWorldAndThrow() {
        System.out.println("i may throw up, but nonetheless: " + greetingService.makeGreeting("world"));
        throw new RuntimeException("sorry");
    }

    @Auditable
    public void auditableHello(int a, float b, String c) {
        System.out.println("I'm auditable; a=" + a + ", b=" + b + ", c=[" + c + "]; " + greetingService.makeGreeting("world"));
    }

}
