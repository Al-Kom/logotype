package com.softarexpractice.logotype.service;

import com.softarexpractice.logotype.model.Response;
import com.softarexpractice.logotype.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    private ResponseRepository repository;

    @Autowired

    public ResponseService(ResponseRepository repository) {
        this.repository = repository;
    }

    public Response findById(Long id) {
        return repository.getOne(id);
    }

    public List<Response> findAll() {
        return repository.findAll();
    }

    public Response saveResponse(Response course) {
        return repository.save(course);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
