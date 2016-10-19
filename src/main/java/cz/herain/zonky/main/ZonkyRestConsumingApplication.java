package cz.herain.zonky.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = ZonkyRestConsumingApplication.PACKAGE_TO_SCAN)
public class ZonkyRestConsumingApplication {

	public static final String PACKAGE_TO_SCAN = "cz.herain.zonky";

	public static void main(String[] args) {
		SpringApplication.run(ZonkyRestConsumingApplication.class, args);
	}
}
