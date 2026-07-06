package com.climalert.services.impl;

import com.climalert.dto.clima.WeatherApiResponseDTO;
import com.climalert.models.entities.clima.Clima;
import com.climalert.models.repositories.ClimaRepository;
import com.climalert.services.ClimaService;
import com.climalert.services.clients.WeatherApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClimaServiceImpl implements ClimaService {

  private final WeatherApiClient weatherApiClient;
  private final ClimaRepository climaRepository;

  public ClimaServiceImpl(WeatherApiClient weatherApiClient,
                          ClimaRepository climaRepository) {
    this.weatherApiClient = weatherApiClient;
    this.climaRepository = climaRepository;
  }

  @Override
  public Clima actualizarClima() {
    WeatherApiResponseDTO respuesta = weatherApiClient.obtenerClimaActual();
    Clima clima = mapear(respuesta);
    Clima guardado = climaRepository.save(clima);
    log.info("Clima guardado para {}: {} C / {} %",
        guardado.getUbicacion(), guardado.getTemperaturaCelsius(), guardado.getHumedad());
    return guardado;
  }

  @Override
  public List<Clima> obtenerHistorial() {
    return climaRepository.findAll();
  }

  @Override
  public Optional<Clima> obtenerUltimo() {
    return climaRepository.findUltimo();
  }

  private Clima mapear(WeatherApiResponseDTO dto) {
    WeatherApiResponseDTO.Location loc = dto.location();
    WeatherApiResponseDTO.Current cur = dto.current();

    Clima clima = new Clima();
    clima.setUbicacion(loc != null ? loc.name() : null);
    clima.setTemperaturaCelsius(cur.tempC());
    clima.setSensacionTermicaCelsius(cur.feelslikeC());
    clima.setHumedad(cur.humidity());
    clima.setVelocidadVientoKmh(cur.windKph());
    clima.setCondicion(cur.condition() != null ? cur.condition().text() : null);
    return clima;
  }
}
