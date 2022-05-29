package com.developer.naturalfisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendNaturalFisherApplication {
	
	private static String version = "1.0 Rel 3.1 RS 0007 28/05/2022";
	private static String versionStr = "=======================================================  Version:";

	public static void main(String[] args) {
		SpringApplication.run(BackendNaturalFisherApplication.class, args);
		System.out.println(versionStr + " " + version + " =======================================================");
	}

}
