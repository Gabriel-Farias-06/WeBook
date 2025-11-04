package com.webook.app.UI.api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HealthzController {
  @GetMapping("/healthz")
  public Map<String,String> healthz() { return Map.of("status","UP"); }
}
