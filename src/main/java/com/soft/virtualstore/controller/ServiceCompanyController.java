package com.soft.virtualstore.controller;

import com.soft.virtualstore.model.ServiceCompany;
import com.soft.virtualstore.model.Services;
import com.soft.virtualstore.repository.ServiceCompanyRepository;
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
@RequestMapping("/serviceCompany")
public class ServiceCompanyController {

    @Autowired
    private ServiceCompanyRepository serviceCompanyRepository;

    @ApiOperation("Register service one at a time")
    @PostMapping
    public ResponseEntity<ServiceCompany> save(@RequestBody ServiceCompany serviceCompany) {
        serviceCompanyRepository.save(serviceCompany);
        return new ResponseEntity<>(serviceCompany, HttpStatus.OK);
    }

    @ApiOperation("Service query, returning all in one list")
    @GetMapping
    public ResponseEntity<List<ServiceCompany>> getAll() {
        List<ServiceCompany> serviceCompanyList = new ArrayList<>();
        serviceCompanyList = serviceCompanyRepository.findAll();
        return new ResponseEntity<>(serviceCompanyList, HttpStatus.OK);
    }

    @ApiOperation("Service query, one at a time")
    @GetMapping(path = "/service/{id}")
    public ResponseEntity<List<ServiceCompany>> getById(@PathVariable Long id) {
        List<ServiceCompany> serviceCompanyId;
        try {
            serviceCompanyId = serviceCompanyRepository.findByServicesId(id);
            return ResponseEntity.ok().body(serviceCompanyId);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Deletes service, one at a time")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<ServiceCompany>> deleteById(@PathVariable Long id) {
        try {
            serviceCompanyRepository.deleteById(id);
            return new ResponseEntity<Optional<ServiceCompany>>(HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<Optional<ServiceCompany>>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Updates service, one at a time")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ServiceCompany> update(@PathVariable Long id, @RequestBody ServiceCompany newServiceCompany) {
        return serviceCompanyRepository.findById(id)
                .map(serviceCompany -> {
                    serviceCompany.setServices(newServiceCompany.getServices());
                    serviceCompany.setCompany(newServiceCompany.getCompany());
                    ServiceCompany servicesCompanyUpdate = serviceCompanyRepository.save(serviceCompany);
                    return ResponseEntity.ok().body(servicesCompanyUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }
}