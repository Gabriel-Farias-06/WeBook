package com.webook.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(servers = { @Server(url = "/", description = "Default Server")})
@SpringBootApplication(
    scanBasePackages = "com.webook.app",
    exclude = { UserDetailsServiceAutoConfiguration.class }
)
@EnableJpaRepositories(basePackages = "com.webook.app.Infraestructure.Repositories")
@EntityScan(basePackages = "com.webook.app.domain")
public class AppApplication {
  public static void main(String[] args) {
    SpringApplication.run(AppApplication.class, args);
  }
}
