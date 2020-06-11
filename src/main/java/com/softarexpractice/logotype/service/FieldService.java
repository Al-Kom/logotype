package com.softarexpractice.logotype.service;

import com.softarexpractice.logotype.model.Field;
import com.softarexpractice.logotype.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {
    private FieldRepository repository;

    @Autowired
    public FieldService(FieldRepository repository) {
        this.repository = repository;
    }

    public Field findById(Long id) {
        return repository.getOne(id);
    }

    public List<Field> findAll() {
        return repository.findAll();
    }

    public Field saveField(Field course) {
        return repository.save(course);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
