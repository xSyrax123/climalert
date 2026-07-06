package com.climalert.controllers;

import com.climalert.dto.clima.ClimaMapper;
import com.climalert.dto.clima.ClimaResponseDTO;
import com.climalert.exceptions.ResourceNotFoundException;
import com.climalert.services.ClimaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${spring.application.name}/clima")
public class ClimaController {

  private final ClimaService climaService;

  public ClimaController(ClimaService climaService) {
    this.climaService = climaService;
  }

  @GetMapping
  public ResponseEntity<List<ClimaResponseDTO>> historial() {
    List<ClimaResponseDTO> historial = climaService.obtenerHistorial().stream()
        .map(ClimaMapper::toDTO)
        .toList();
    return ResponseEntity.ok(historial);
  }

  @GetMapping("/ultimo")
  public ResponseEntity<ClimaResponseDTO> ultimo() {
    return climaService.obtenerUltimo()
        .map(ClimaMapper::toDTO)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new ResourceNotFoundException("Todavia no hay lecturas de clima"));
  }
}
