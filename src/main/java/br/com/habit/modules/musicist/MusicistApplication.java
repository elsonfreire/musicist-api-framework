package br.com.habit.modules.musicist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"br.com.habit.infra", "br.com.habit.modules.framework", "br.com.habit.modules.musicist"})
@EntityScan(basePackages = {"br.com.habit.infra", "br.com.habit.modules.framework", "br.com.habit.modules.musicist"})
@EnableJpaRepositories(basePackages = {"br.com.habit.infra", "br.com.habit.modules.framework", "br.com.habit.modules.musicist"})
public class MusicistApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicistApplication.class, args);
    }
}
