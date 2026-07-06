package com.climalert.services;

import com.climalert.models.entities.canal.CanalNotificacion;
import com.climalert.models.entities.notificacion.Notificacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncNotificacionEnviar {

  @Async("climalertExecutor")
  public void enviarAsync(Notificacion notificacion, CanalNotificacion canal) {
    try {
      canal.enviar(notificacion);
      notificacion.marcarComoEnviada();
    } catch (Exception e) {
      log.error("No se pudo enviar la notificacion a {}: {}",
          notificacion.getDestinatario(), e.getMessage());
      notificacion.marcarComoFallida();
    }
  }
}
