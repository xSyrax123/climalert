package com.climalert.models.entities.canal;

import com.climalert.models.entities.notificacion.Notificacion;

public interface CanalNotificacion {
  void enviar(Notificacion notificacion);
}
