package com.climalert.dto.clima;

import com.climalert.models.entities.clima.Clima;

public class ClimaMapper {

  public static ClimaResponseDTO toDTO(Clima clima) {
    return new ClimaResponseDTO(
        clima.getId(),
        clima.getUbicacion(),
        clima.getTemperaturaCelsius(),
        clima.getSensacionTermicaCelsius(),
        clima.getHumedad(),
        clima.getCondicion(),
        clima.getVelocidadVientoKmh(),
        clima.esCondicionDeAlerta(),
        clima.isProcesado(),
        clima.getFechaRegistro()
    );
  }
}
