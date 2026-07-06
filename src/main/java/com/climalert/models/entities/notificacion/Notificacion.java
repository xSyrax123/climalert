package com.climalert.models.entities.notificacion;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Notificacion {

  private final String destinatario;
  private final String asunto;
  private final String cuerpo;
  private final LocalDateTime fechaCreacion;
  private EstadoNotificacion estado;

  public Notificacion(String destinatario, String asunto, String cuerpo) {
    this.destinatario = destinatario;
    this.asunto = asunto;
    this.cuerpo = cuerpo;
    this.fechaCreacion = LocalDateTime.now();
    this.estado = EstadoNotificacion.PENDIENTE;
  }

  public void marcarComoEnviada() {
    this.estado = EstadoNotificacion.ENVIADO;
  }

  public void marcarComoFallida() {
    this.estado = EstadoNotificacion.FALLIDO;
  }
}
