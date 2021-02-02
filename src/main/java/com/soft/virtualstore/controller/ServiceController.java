package com.soft.virtualstore.controller;

import com.soft.virtualstore.model.ServiceCard;
import com.soft.virtualstore.repository.ServiceRepository;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/service")
public class ServiceController {

    private ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository){
        super();
        this.serviceRepository = serviceRepository;
    }

    @ApiOperation("Register service one at a time")
   @PostMapping
    public ResponseEntity<ServiceCard> save(@RequestBody ServiceCard serviceCard){
        serviceRepository.save(serviceCard);
        return new ResponseEntity<>(serviceCard, HttpStatus.OK);
   }

    @ApiOperation("Service query, returning all in one list")
    @GetMapping
    public ResponseEntity<List<ServiceCard>> getAll(){
        List<ServiceCard> serviceCardList = new ArrayList<>();
        serviceCardList = serviceRepository.findAll();
        return  new ResponseEntity<>(serviceCardList, HttpStatus.OK);
    }

    @ApiOperation("Service query, one at a time")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ServiceCard>> getById(@PathVariable Integer id){
        Optional<ServiceCard> serviceCardId;
        try {
            serviceCardId = serviceRepository.findById(id);
            return new ResponseEntity<Optional<ServiceCard>>(serviceCardId, HttpStatus.OK);
        }catch (NoSuchElementException exception){
            return new ResponseEntity<Optional<ServiceCard>>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Deletes service, one at a time")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<ServiceCard>> deleteById(@PathVariable Integer id){
        try {
            serviceRepository.deleteById(id);
            return new ResponseEntity<Optional<ServiceCard>>(HttpStatus.OK);
        }catch (NoSuchElementException exception){
            return new ResponseEntity<Optional<ServiceCard>>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Updates service, one at a time")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ServiceCard> update(@PathVariable Integer id, @RequestBody ServiceCard newPerson){
        return serviceRepository.findById(id)
                .map(person -> {
                    person.setName(newPerson.getName());
                    person.setImage(newPerson.getImage());
                    ServiceCard personUpdate = serviceRepository.save(person);
                    return ResponseEntity.ok().body(personUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }

}
