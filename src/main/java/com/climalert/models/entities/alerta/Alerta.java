package com.climalert.models.entities.alerta;

import com.climalert.models.entities.clima.Clima;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Alerta {

  private Long id;
  private Clima clima;
  private LocalDateTime fechaGeneracion;

  public Alerta() {
    this.fechaGeneracion = LocalDateTime.now();
  }

  public Alerta(Clima clima) {
    this();
    this.clima = clima;
  }

  public String asunto() {
    return "ALERTA climatica en " + clima.getUbicacion();
  }

  public String detalleCompleto() {
    return """
            Se detectaron condiciones climaticas peligrosas.

            Ubicacion: %s
            Temperatura: %.1f C
            Sensacion termica: %.1f C
            Humedad: %d %%
            Condicion: %s
            Viento: %.1f km/h
            Fecha de la lectura: %s

            Umbrales de alerta: temperatura > %.0f C y humedad > %d %%.
            """.formatted(
        clima.getUbicacion(),
        clima.getTemperaturaCelsius(),
        clima.getSensacionTermicaCelsius(),
        clima.getHumedad(),
        clima.getCondicion(),
        clima.getVelocidadVientoKmh(),
        clima.getFechaRegistro(),
        Clima.UMBRAL_TEMPERATURA,
        Clima.UMBRAL_HUMEDAD
    );
  }
}
