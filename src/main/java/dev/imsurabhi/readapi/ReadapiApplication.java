package dev.imsurabhi.readapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"dev.imsurabhi"})
@SpringBootApplication
public class ReadapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadapiApplication.class, args);
		//System.out.println((System.currentTimeMillis()).getClass().getSimpleName());
	}

}
