package cz.codecamp.aop;

import cz.codecamp.aop.service.HelloWorldService;
import cz.codecamp.aop.service.TimeTrackedService;
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

		context.getBean(TimeTrackedService.class).iWantToBeTimeTracked(2000, 3);
		context.close();
	}
}
