package com.climalert.services.clients;

import com.climalert.dto.clima.WeatherApiResponseDTO;

public interface WeatherApiClient {

  WeatherApiResponseDTO obtenerClimaActual();
}
