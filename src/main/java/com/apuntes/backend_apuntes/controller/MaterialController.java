package com.apuntes.backend_apuntes.controller;

import com.apuntes.backend_apuntes.controller.dto.MaterialRequest;
import com.apuntes.backend_apuntes.controller.dto.MaterialResponse;
import com.apuntes.backend_apuntes.model.Material;
import com.apuntes.backend_apuntes.model.Usuario;
import com.apuntes.backend_apuntes.service.MaterialService;
import com.apuntes.backend_apuntes.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiales")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;
    private final MaterialRepository materialRepository;

    @GetMapping
    public ResponseEntity<List<MaterialResponse>> listarMateriales(
            @RequestParam(value = "materiaId", required = false) Long materiaId) {

        List<Material> materiales = materialService.listarMateriales(materiaId);

        List<MaterialResponse> respuesta = materiales.stream()
                .map(m -> MaterialResponse.builder()
                        .id(m.getId())
                        .titulo(m.getTitulo())
                        .descripcion(m.getDescripcion())
                        .precio(m.getPrecio())
                        .materiaNombre(m.getMateria().getName())
                        .materiaCodigo(m.getMateria().getCode())
                        .usuarioNombre(m.getUsuario().getNombreUsuario())
                        .build())
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<MaterialResponse> crearMaterial(
            @RequestBody MaterialRequest request,
            @AuthenticationPrincipal Usuario usuarioAutenticado) {

        Material material = materialService.crearMaterial(request, usuarioAutenticado);
        return ResponseEntity.status(HttpStatus.CREATED).body(MaterialResponse.builder()
                .id(material.getId())
                .titulo(material.getTitulo())
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMaterial(@PathVariable Long id) {
        if (materialRepository.existsById(id)) {
            materialRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
