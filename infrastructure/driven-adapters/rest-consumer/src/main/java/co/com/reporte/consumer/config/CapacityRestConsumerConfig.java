package co.com.reporte.consumer.config;

import static co.com.reporte.consumer.config.HttpConnector.getClientHttpConnector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CapacityRestConsumerConfig {

  private final String url;

  private final int timeout;

  public CapacityRestConsumerConfig(@Value("${adapter.capacity.restconsumer.url}") String url,
      @Value("${adapter.capacity.restconsumer.timeout}") int timeout) {
    this.url = url;
    this.timeout = timeout;
  }

  @Bean("capacityWebClient")
  public WebClient getCapacityWebClient(WebClient.Builder builder) {
    return builder
        .baseUrl(url)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        .clientConnector(getClientHttpConnector(timeout))
        .build();
  }

}
