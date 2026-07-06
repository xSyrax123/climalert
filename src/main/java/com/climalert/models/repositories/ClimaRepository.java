package com.climalert.models.repositories;

import com.climalert.models.entities.clima.Clima;

import java.util.List;
import java.util.Optional;

public interface ClimaRepository {

  Clima save(Clima clima);

  List<Clima> findAll();

  List<Clima> findNoProcesados();

  Optional<Clima> findUltimo();
}
