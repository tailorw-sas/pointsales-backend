package com.kynsof.treatments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TreatmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreatmentsApplication.class, args);
	}


}
