package com.climalert.dto.clima;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiResponseDTO(
    Location location,
    Current current
) {
  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Location(
      String name,
      String region,
      String country
  ) {}

  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Current(
      @JsonProperty("temp_c") Double tempC,
      @JsonProperty("feelslike_c") Double feelslikeC,
      Integer humidity,
      @JsonProperty("wind_kph") Double windKph,
      Condition condition
  ) {}

  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Condition(
      String text
  ) {}
}
