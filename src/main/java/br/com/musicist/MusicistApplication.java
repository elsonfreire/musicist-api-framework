package br.com.musicist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MusicistApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicistApplication.class, args);
	}

}
