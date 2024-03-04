package com.example.condominiumApi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Fast Assembly API", version = "1", description = "API  p/ Fast Assembly"), servers = { @Server(url = "/") })
public class CalculusExpressionApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(CalculusExpressionApiApplication.class, args);
	}
}

