package com.climalert.models.repositories.inMemory;

import com.climalert.models.entities.clima.Clima;
import com.climalert.models.repositories.ClimaRepository;
import com.climalert.models.utils.GeneradorIdSecuencial;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryClimaRepository implements ClimaRepository {

  private final List<Clima> climas = new ArrayList<>();
  private final GeneradorIdSecuencial generadorId = new GeneradorIdSecuencial();

  @Override
  public synchronized Clima save(Clima clima) {
    if (clima.getId() == null) {
      clima.setId(generadorId.siguiente());
      climas.add(clima);
    }

    return clima;
  }

  @Override
  public synchronized List<Clima> findAll() {
    return new ArrayList<>(climas);
  }

  @Override
  public synchronized List<Clima> findNoProcesados() {
    return climas.stream()
        .filter(c -> !c.isProcesado())
        .toList();
  }

  @Override
  public synchronized Optional<Clima> findUltimo() {
    return climas.stream()
        .max(Comparator.comparing(Clima::getFechaRegistro));
  }
}
