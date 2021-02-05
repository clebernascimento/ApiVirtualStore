package com.soft.virtualstore.controller;

import com.soft.virtualstore.model.Services;
import com.soft.virtualstore.repository.ServiceRepository;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/service")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @ApiOperation("Register service one at a time")
    @PostMapping("/register/service")
    public ResponseEntity<Services> save(@RequestBody Services services) {
        serviceRepository.save(services);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @ApiOperation("Service query, returning all in one list")
    @GetMapping
    public ResponseEntity<List<Services>> getAll() {
        List<Services> servicesList = new ArrayList<>();
        servicesList = serviceRepository.findAll();
        return new ResponseEntity<>(servicesList, HttpStatus.OK);
    }

    @ApiOperation("Service query, one at a time")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Services>> getById(@PathVariable Long id) {
        Optional<Services> serviceCardId;
        try {
            serviceCardId = serviceRepository.findById(id);
            return ResponseEntity.ok().body(serviceCardId);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Deletes service, one at a time")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Services>> deleteById(@PathVariable Long id) {
        try {
            serviceRepository.deleteById(id);
            return new ResponseEntity<Optional<Services>>(HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<Optional<Services>>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Updates service, one at a time")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Services> update(@PathVariable Long id, @RequestBody Services newService) {
        return serviceRepository.findById(id)
                .map(serviceCard -> {
                    serviceCard.setName(newService.getName());
                    serviceCard.setImage(newService.getImage());
                    Services servicesUpdate = serviceRepository.save(serviceCard);
                    return ResponseEntity.ok().body(servicesUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }

}
