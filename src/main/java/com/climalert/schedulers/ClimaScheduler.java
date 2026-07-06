package com.climalert.schedulers;

import com.climalert.services.ClimaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClimaScheduler {

  private final ClimaService climaService;

  public ClimaScheduler(ClimaService climaService) {
    this.climaService = climaService;
  }

  @Scheduled(fixedRateString = "${scheduler.clima.fixed-rate:300000}")
  public void actualizarClima() {
    try {
      climaService.actualizarClima();
    } catch (Exception e) {
      // No propagamos: el scheduler debe seguir corriendo en el proximo ciclo.
      log.error("Error al actualizar el clima: {}", e.getMessage());
    }
  }
}
