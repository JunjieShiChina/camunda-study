package me.shijunjiee.camundastudy;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CamundaStudyApplication implements CommandLineRunner {
	private static final Logger logger= LoggerFactory.getLogger(CamundaStudyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CamundaStudyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(" CamundaStudyApplication start");
	}
}
