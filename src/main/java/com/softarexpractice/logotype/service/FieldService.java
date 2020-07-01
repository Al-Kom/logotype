package com.softarexpractice.logotype.service;

import com.softarexpractice.logotype.model.Field;
import com.softarexpractice.logotype.model.User;
import com.softarexpractice.logotype.repository.FieldRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log
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

    public List<Field> findByUser(User user) {
        return repository.findAll().stream()
                .filter(field -> field.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    public Field findByIdAndUser(Long id, User user) {
        Field field = findById(id);
        log.info("input user id: " + user.getId() + ", found user id: "
                + field.getUser().getId());
        if (field.getUser().getId().equals(user.getId()))
            return field;
        log.info("return null field");
        return null;
    }
}
