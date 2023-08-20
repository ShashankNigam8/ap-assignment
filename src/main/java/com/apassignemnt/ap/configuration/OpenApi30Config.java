package com.apassignemnt.ap.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "AP Assignment API", version = "v1"))
@SecurityScheme(name = "bearerAuth" , type = SecuritySchemeType.HTTP, scheme = "bearer")
public class OpenApi30Config {
}
