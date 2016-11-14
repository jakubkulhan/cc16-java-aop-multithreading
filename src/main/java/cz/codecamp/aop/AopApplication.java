package cz.codecamp.aop;

import cz.codecamp.aop.service.HelloWorldService;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AopApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AopApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext context = application.run(args);

        context.getBean(HelloWorldService.class).sayHelloWorld();

        try {
            context.getBean(HelloWorldService.class).sayHelloWorldAndThrow();
        } catch (Throwable t) {
            // do nothing
        }

        context.getBean(HelloWorldService.class).auditableHello(1, 5.0f, "this is a string");

        context.close();
    }
}
