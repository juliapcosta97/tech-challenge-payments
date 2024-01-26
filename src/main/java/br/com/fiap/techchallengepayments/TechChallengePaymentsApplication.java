package br.com.fiap.techchallengepayments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.fiap.techchallengepayments")
@EnableFeignClients
public class TechChallengePaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechChallengePaymentsApplication.class, args);
	}
}
