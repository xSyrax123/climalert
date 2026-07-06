package com.climalert.services;

import com.climalert.models.entities.alerta.Alerta;

public interface NotificacionService {

  void notificarAlerta(Alerta alerta);
}
