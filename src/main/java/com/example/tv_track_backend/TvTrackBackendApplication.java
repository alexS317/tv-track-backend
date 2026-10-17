package com.example.tv_track_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TvTrackBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvTrackBackendApplication.class, args);
	}

}
