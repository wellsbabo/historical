package kr.co.gravity.sample;

import wellsbabo.common.config.CustomBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(nameGenerator = CustomBeanNameGenerator.class)
public class HistoricalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoricalApplication.class, args);
	}

}
