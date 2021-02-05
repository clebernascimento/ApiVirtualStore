package com.soft.virtualstore.controller;

import com.soft.virtualstore.dto.ServiceCompanyDTO;
import com.soft.virtualstore.model.Company;
import com.soft.virtualstore.model.ServiceCompany;
import com.soft.virtualstore.model.Services;
import com.soft.virtualstore.repository.CompanyRepository;
import com.soft.virtualstore.repository.ServiceCompanyRepository;
import com.soft.virtualstore.repository.ServiceRepository;
import com.soft.virtualstore.validation.ObjectNotFoundException;
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

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @ApiOperation("Register service one at a time")
    @PostMapping("/register")
    public ResponseEntity<ServiceCompany> save(@RequestBody ServiceCompanyDTO dto) {
        ServiceCompany serviceCompany = new ServiceCompany();

        persist(dto, serviceCompany);

        return ResponseEntity.ok().body(serviceCompany);
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
    public ResponseEntity<ServiceCompany> update(@PathVariable Long id, @RequestBody ServiceCompanyDTO dto) {
        ServiceCompany serviceCompany = serviceCompanyRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado: " + id));

        persist(dto, serviceCompany);

        return ResponseEntity.ok().body(serviceCompany);
    }

    private void persist(@RequestBody ServiceCompanyDTO dto, ServiceCompany serviceCompany) {
        Services services = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new ObjectNotFoundException("Serviço não encontrado para o id: "+ dto.getServiceId()));

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new ObjectNotFoundException("Empresa não encontrada para o id: "+ dto.getCompanyId()));

        serviceCompany.setCompany(company);
        serviceCompany.setServices(services);

        serviceCompanyRepository.save(serviceCompany);
    }
}