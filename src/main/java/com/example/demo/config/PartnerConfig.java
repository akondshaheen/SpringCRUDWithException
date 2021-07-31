package com.example.demo.config;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Partners;
import com.example.demo.repo.PartnerRepo;

@Configuration
public class PartnerConfig {

	@Bean
	CommandLineRunner commandLineRunner(
			PartnerRepo partnerRepo) {
		   LocalDate date = LocalDate.of(2023,01,01);

		return args -> {
			Partners Roche =new Partners("Roche", "Spain", Locale.ROOT, date);
			Partners Sparker =new Partners("Sparker", "Spain", Locale.ROOT, date);
			Partners BCN =new Partners("BCN", "Spain", Locale.ROOT, date);
			Partners Ldn =new Partners("LDN", "UK", Locale.UK, date);
			Partners NY =new Partners("NY", "USA", Locale.US, date);
			Partners Ps =new Partners("PS", "France", Locale.FRANCE, date);
			
			partnerRepo.saveAll(List.of(Roche, Sparker, BCN, Ldn, NY, Ps));

		};
	}
}
