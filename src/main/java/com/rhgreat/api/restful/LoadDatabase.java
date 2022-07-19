package com.rhgreat.api.restful;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository repository) {
        return args -> {
            long ms = new Date().getTime();
            java.sql.Date date = new java.sql.Date(ms);

            log.info("Preloading " + repository.save(new Usuario("Jerônimo", "Silvana", "11111111111", "111111111111", date)));
            log.info("Preloading " + repository.save(new Usuario("Josias", "Roberta", "22222222222", "111111111111", date)));
        };
    }
}
