package home.ahmad.boot_joinfaces.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class AppConfig {

	@Value("${api.token}")
	private String apiToken;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BearerTokenInterceptor(apiToken));
        return restTemplate;
    }

    static class BearerTokenInterceptor implements ClientHttpRequestInterceptor {
        private final String token;

        public BearerTokenInterceptor(String token) {
            this.token = token;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            System.out.println("Setting Authorization header: Bearer " + token); 
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            return execution.execute(request, body);
        }
    }
}

