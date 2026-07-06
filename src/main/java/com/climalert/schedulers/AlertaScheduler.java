package com.climalert.schedulers;

import com.climalert.services.AlertaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AlertaScheduler {

  private final AlertaService alertaService;

  public AlertaScheduler(AlertaService alertaService) {
    this.alertaService = alertaService;
  }

  @Scheduled(fixedRateString = "${scheduler.alertas.fixed-rate:60000}")
  public void procesarAlertas() {
    try {
      alertaService.procesarAlertas();
    } catch (Exception e) {
      log.error("Error al procesar alertas: {}", e.getMessage());
    }
  }
}
