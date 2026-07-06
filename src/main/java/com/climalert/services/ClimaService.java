package com.climalert.services;

import com.climalert.models.entities.clima.Clima;

import java.util.List;
import java.util.Optional;

public interface ClimaService {

  Clima actualizarClima();

  List<Clima> obtenerHistorial();

  Optional<Clima> obtenerUltimo();
}
