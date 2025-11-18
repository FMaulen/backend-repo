package com.apuntes.backend_apuntes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apuntes.backend_apuntes.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findByMateriaId(Long materiaId);
}
