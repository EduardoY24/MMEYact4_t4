package com.eymm.mmeyact4_t4.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eymm.mmeyact4_t4.dto.VideojuegoRequest;
import com.eymm.mmeyact4_t4.dto.VideojuegoResponse;
import com.eymm.mmeyact4_t4.service.VideojuegoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoService service;

    @GetMapping
    public ResponseEntity<Page<VideojuegoResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoResponse> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<VideojuegoResponse> crear(@Valid @RequestBody VideojuegoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoResponse> actualizar(@PathVariable Integer id,
                                                         @Valid @RequestBody VideojuegoRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Videojuego eliminado"));
    }
}