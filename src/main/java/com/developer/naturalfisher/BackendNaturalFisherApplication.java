package com.developer.naturalfisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendNaturalFisherApplication {
	
	private static String version = "1.0 Rel 1.8 RS 0008 09/12/2022"; //ML 28/07/2022 Fase IV Desarrollo
	private static String versionStr = "=======================================================  Version:";

	public static void main(String[] args) {
		SpringApplication.run(BackendNaturalFisherApplication.class, args);
		System.out.println(versionStr + " " + version + " =======================================================");
	}

}
