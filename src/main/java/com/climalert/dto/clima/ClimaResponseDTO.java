package com.climalert.dto.clima;

import java.time.LocalDateTime;

public record ClimaResponseDTO(
    Long id,
    String ubicacion,
    Double temperaturaCelsius,
    Double sensacionTermicaCelsius,
    Integer humedad,
    String condicion,
    Double velocidadVientoKmh,
    boolean esAlerta,
    boolean procesado,
    LocalDateTime fechaRegistro
) {}
