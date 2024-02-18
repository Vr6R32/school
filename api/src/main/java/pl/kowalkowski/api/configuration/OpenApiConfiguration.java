package pl.kowalkowski.api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
class OpenApiConfiguration {
    private static final String SITE_URL = "https://mk.pl/";

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(appName)
                        .description("OpenApi documentation for " + appName)
                        .version("1.0")
                        .contact(new Contact()
                                .name("Michal Kowalkowski")
                                .email("michal.kowalkowski.dev@gmail.com")
                                )
                        .description("SCHOOL API PROJECT")
                        .license(new License()
                                .name("MK-COMPANY")
                                .url(SITE_URL)))
                .addServersItem(new Server().url("http://localhost:27017").description("LOCAL HTTP ENV"))
                .addServersItem(new Server().url(SITE_URL).description("PROD ENV"));
    }
}
