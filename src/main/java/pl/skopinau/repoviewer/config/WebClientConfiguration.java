package pl.skopinau.repoviewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    private static final String BASE_URL = "https://api.github.com";

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
