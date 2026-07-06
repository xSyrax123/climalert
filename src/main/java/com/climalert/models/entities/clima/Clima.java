package com.climalert.models.entities.clima;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Clima {

  public static final double UMBRAL_TEMPERATURA = 35.0;
  public static final int UMBRAL_HUMEDAD = 60;

  private Long id;
  private String ubicacion;
  private Double temperaturaCelsius;
  private Integer humedad;
  private String condicion;
  private Double velocidadVientoKmh;
  private Double sensacionTermicaCelsius;
  private LocalDateTime fechaRegistro;
  private boolean procesado;

  public Clima() {
    this.fechaRegistro = LocalDateTime.now();
    this.procesado = false;
  }

  public boolean esCondicionDeAlerta() {
    if (temperaturaCelsius == null || humedad == null) {
      return false;
    }
    return temperaturaCelsius > UMBRAL_TEMPERATURA && humedad > UMBRAL_HUMEDAD;
  }

  public void marcarProcesado() {
    this.procesado = true;
  }
}
