package com.example.condominiumApi.config;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@Slf4j
@Log4j2
public class MvcConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}