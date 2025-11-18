package com.apuntes.backend_apuntes.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apuntes.backend_apuntes.controller.dto.MaterialRequest;
import com.apuntes.backend_apuntes.model.Materia;
import com.apuntes.backend_apuntes.model.Material;
import com.apuntes.backend_apuntes.model.Usuario;
import com.apuntes.backend_apuntes.repository.MateriaRepository;
import com.apuntes.backend_apuntes.repository.MaterialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MateriaRepository materiaRepository;

    public List<Material> listarMateriales(Long materiaId) {
        if (materiaId != null) {
            return materialRepository.findByMateriaId(materiaId);
        }
        return materialRepository.findAll();
    }

    public Material crearMaterial(MaterialRequest request, Usuario usuario) {

        Materia materia = materiaRepository.findById(request.getMateriaId())
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada"));

        Material material = new Material();
        material.setTitulo(request.getTitulo());
        material.setDescripcion(request.getDescripcion());
        material.setPrecio(request.getPrecio());
        material.setFechaCreacion(LocalDateTime.now());
        material.setMateria(materia);
        material.setUsuario(usuario);

        return materialRepository.save(material);
    }
}
