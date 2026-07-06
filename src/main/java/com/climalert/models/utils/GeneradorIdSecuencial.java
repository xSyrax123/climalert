package com.climalert.models.utils;

import java.util.concurrent.atomic.AtomicLong;

public class GeneradorIdSecuencial {

  private final AtomicLong contador = new AtomicLong(1);

  public Long siguiente() {
    return contador.getAndIncrement();
  }
}
