package com.climalert.services.impl;

import com.climalert.models.entities.alerta.Alerta;
import com.climalert.models.entities.clima.Clima;
import com.climalert.models.repositories.AlertaRepository;
import com.climalert.models.repositories.ClimaRepository;
import com.climalert.services.AlertaService;
import com.climalert.services.NotificacionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AlertaServiceImpl implements AlertaService {

  private final ClimaRepository climaRepository;
  private final AlertaRepository alertaRepository;
  private final NotificacionService notificacionService;

  public AlertaServiceImpl(ClimaRepository climaRepository,
                           AlertaRepository alertaRepository,
                           NotificacionService notificacionService) {
    this.climaRepository = climaRepository;
    this.alertaRepository = alertaRepository;
    this.notificacionService = notificacionService;
  }

  @Override
  public void procesarAlertas() {
    List<Clima> pendientes = climaRepository.findNoProcesados();
    if (pendientes.isEmpty()) {
      return;
    }
    log.info("Analizando {} lectura(s) de clima", pendientes.size());

    for (Clima clima : pendientes) {
      if (clima.esCondicionDeAlerta()) {
        Alerta alerta = alertaRepository.save(new Alerta(clima));
        notificacionService.notificarAlerta(alerta);
        log.warn("Alerta generada para {} ({} C / {} %)",
            clima.getUbicacion(), clima.getTemperaturaCelsius(), clima.getHumedad());
      }
      clima.marcarProcesado();
      climaRepository.save(clima);
    }
  }
}
