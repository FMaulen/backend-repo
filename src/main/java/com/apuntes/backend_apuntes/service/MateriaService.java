package com.apuntes.backend_apuntes.service;

import com.apuntes.backend_apuntes.model.Materia;
import com.apuntes.backend_apuntes.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MateriaService {

    private final MateriaRepository materiaRepository;

    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }

}
