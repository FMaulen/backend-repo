package com.apuntes.backend_apuntes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apuntes.backend_apuntes.model.Materia;
import com.apuntes.backend_apuntes.service.MateriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor


public class MateriaController {

    private final MateriaService materiaService;
    
    @GetMapping

    public ResponseEntity<List<Materia>> getAllMaterias() {
        return ResponseEntity.ok(materiaService.findAll());
    }

}
