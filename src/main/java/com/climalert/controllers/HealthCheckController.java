package com.climalert.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${spring.application.name}/health")
public class HealthCheckController {

  @GetMapping
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Climalert operativo.");
  }
}
