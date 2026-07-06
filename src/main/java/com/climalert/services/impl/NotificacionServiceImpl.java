package com.climalert.services.impl;

import com.climalert.models.entities.alerta.Alerta;
import com.climalert.models.entities.canal.CanalNotificacion;
import com.climalert.models.entities.notificacion.Notificacion;
import com.climalert.services.AsyncNotificacionEnviar;
import com.climalert.services.NotificacionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NotificacionServiceImpl implements NotificacionService {

  private final CanalNotificacion canalEmail;
  private final AsyncNotificacionEnviar asyncNotificacionEnviar;
  private final List<String> destinatarios;

  public NotificacionServiceImpl(
      CanalNotificacion canalEmail,
      AsyncNotificacionEnviar asyncNotificacionEnviar,
      @Value("${alertas.destinatarios}") String destinatarios
  ) {
    this.canalEmail = canalEmail;
    this.asyncNotificacionEnviar = asyncNotificacionEnviar;
    this.destinatarios = List.of(destinatarios.split("\\s*,\\s*"));
  }

  @Override
  public void notificarAlerta(Alerta alerta) {
    String asunto = alerta.asunto();
    String cuerpo = alerta.detalleCompleto();

    log.info("Generando alerta para {} destinatarios", destinatarios.size());
    for (String destinatario : destinatarios) {
      Notificacion notificacion = new Notificacion(destinatario, asunto, cuerpo);
      asyncNotificacionEnviar.enviarAsync(notificacion, canalEmail);
    }
  }
}
