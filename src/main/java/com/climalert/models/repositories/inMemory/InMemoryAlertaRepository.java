package com.climalert.models.repositories.inMemory;

import com.climalert.models.entities.alerta.Alerta;
import com.climalert.models.repositories.AlertaRepository;
import com.climalert.models.utils.GeneradorIdSecuencial;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryAlertaRepository implements AlertaRepository {

  private final List<Alerta> alertas = new ArrayList<>();
  private final GeneradorIdSecuencial generadorId = new GeneradorIdSecuencial();

  @Override
  public synchronized Alerta save(Alerta alerta) {
    if (alerta.getId() == null) {
      alerta.setId(generadorId.siguiente());
    }
    alertas.add(alerta);
    return alerta;
  }

  @Override
  public synchronized List<Alerta> findAll() {
    return new ArrayList<>(alertas);
  }
}
