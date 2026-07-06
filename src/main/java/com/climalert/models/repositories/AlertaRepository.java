package com.climalert.models.repositories;

import com.climalert.models.entities.alerta.Alerta;

import java.util.List;

public interface AlertaRepository {

  Alerta save(Alerta alerta);

  List<Alerta> findAll();
}
