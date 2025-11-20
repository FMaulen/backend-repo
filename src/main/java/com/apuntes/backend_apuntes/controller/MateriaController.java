package com.apuntes.backend_apuntes.controller;

import com.apuntes.backend_apuntes.controller.dto.MateriaRequest;
import com.apuntes.backend_apuntes.model.Materia;
import com.apuntes.backend_apuntes.service.MateriaService;
import com.apuntes.backend_apuntes.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
public class MateriaController {

    private final MateriaService materiaService;
    private final MateriaRepository materiaRepository;

    @GetMapping
    public ResponseEntity<List<Materia>> getAllMaterias() {
        return ResponseEntity.ok(materiaService.findAll());
    }

    @PostMapping
    public ResponseEntity<Materia> crearMateria(@RequestBody MateriaRequest request) {
        Materia m = new Materia();
        m.setName(request.getName());
        m.setCode(request.getCode());
        return ResponseEntity.ok(materiaRepository.save(m));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMateria(@PathVariable Long id) {
        if (materiaRepository.existsById(id)) {
            materiaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
