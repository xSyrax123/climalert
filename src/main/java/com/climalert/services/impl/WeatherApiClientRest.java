package com.climalert.services.impl;

import com.climalert.dto.clima.WeatherApiResponseDTO;
import com.climalert.exceptions.ExternalServiceException;
import com.climalert.services.clients.WeatherApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class WeatherApiClientRest implements WeatherApiClient {

  private final RestTemplate restTemplate;
  private final String baseUrl;
  private final String apiKey;
  private final String ubicacion;

  public WeatherApiClientRest(
      RestTemplate restTemplate,
      @Value("${weather.api.base-url}") String baseUrl,
      @Value("${weather.api.key}") String apiKey,
      @Value("${weather.api.location}") String ubicacion
  ) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
    this.apiKey = apiKey;
    this.ubicacion = ubicacion;
  }

  @Override
  public WeatherApiResponseDTO obtenerClimaActual() {
    String url = UriComponentsBuilder.fromUriString(baseUrl)
        .path("/current.json")
        .queryParam("key", apiKey)
        .queryParam("q", ubicacion)
        .queryParam("aqi", "no")
        .toUriString();

    try {
      WeatherApiResponseDTO respuesta =
          restTemplate.getForObject(url, WeatherApiResponseDTO.class);

      if (respuesta == null || respuesta.current() == null) {
        throw new ExternalServiceException(
            "WeatherAPI devolvio una respuesta vacia para " + ubicacion);
      }
      return respuesta;

    } catch (RestClientException e) {
      throw new ExternalServiceException(
          "No se pudo consultar WeatherAPI: " + e.getMessage());
    }
  }
}
