package com.softarexpractice.logotype.controller;

import com.softarexpractice.logotype.exception.ForbiddenException;
import com.softarexpractice.logotype.exception.NotFoundException;
import com.softarexpractice.logotype.model.Field;
import com.softarexpractice.logotype.model.User;
import com.softarexpractice.logotype.service.FieldService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("field")
public class FieldController {
    @Autowired
    private FieldService service;

    @GetMapping("all")
    public List<Field> list(@AuthenticationPrincipal User user) {
        return service.findByUser(user);
    }

    @GetMapping("{id}")
    public Field getOne(@AuthenticationPrincipal User user,
                        @PathVariable String id) {
        Field field = service.findByIdAndUser(Long.parseLong(id), user);
        if (field == null) {
            log.info("WARN: Field " + id + " is not found");
            throw new NotFoundException();
        }
        return field;
    }

    @PostMapping("create")
    public Field create(@AuthenticationPrincipal User user, @RequestBody Field field) {
        field.setUser(user);
        return service.saveField(field);
    }

    @PutMapping("update")
    public Field update(@AuthenticationPrincipal User user,
                        @RequestBody Field field) {
        if (service.findByIdAndUser(field.getId(), user) == null) {
            log.info("WARN: Field " + field.getLabel() +
                    " is forbidden for user-" + user.getId());
            throw new ForbiddenException();
        }
        return service.saveField(field);
    }

    @DeleteMapping("{id}")
    public List<Field> delete(@AuthenticationPrincipal User user,
                              @PathVariable String id) {
        Field field = service.findById(Long.parseLong(id));
        if (field == null) {
            log.info("WARN: Field " + id + " is not found");
            throw new NotFoundException();
        }
        service.deleteById(field.getId());
        return service.findAll();
    }
}
