package com.soft.virtualstore.controller;

import com.soft.virtualstore.model.Company;
import com.soft.virtualstore.model.Services;
import com.soft.virtualstore.repository.CompanyRepository;
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
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @ApiOperation("Register company one at a time")
    @PostMapping("/register/company")
    public ResponseEntity<Company> save(@RequestBody Company company) {
        companyRepository.save(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @ApiOperation("Company query, returning all in one list")
    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
        List<Company> companyList = new ArrayList<>();
        companyList = companyRepository.findAll();
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @ApiOperation("Company query, one at a time")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Company>> getById(@PathVariable Long id) {
        Optional<Company> companyId;
        try {
            companyId = companyRepository.findById(id);
            return ResponseEntity.ok().body(companyId);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Deletes company, one at a time")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Company>> deleteById(@PathVariable Long id) {
        try {
            companyRepository.deleteById(id);
            return new ResponseEntity<Optional<Company>>(HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            return new ResponseEntity<Optional<Company>>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Updates company, one at a time")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Company> update(@PathVariable Long id, @RequestBody Company newCompany) {
        return companyRepository.findById(id)
                .map(companyWeb -> {
                    companyWeb.setName(newCompany.getName());
                    companyWeb.setImage(newCompany.getImage());
                    Company companyUpdate = companyRepository.save(companyWeb);
                    return ResponseEntity.ok().body(companyUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }
}
