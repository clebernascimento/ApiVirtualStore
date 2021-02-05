package com.soft.virtualstore.repository;

import com.soft.virtualstore.model.ServiceCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceCompanyRepository extends JpaRepository<ServiceCompany, Long> {

    List<ServiceCompany> findByServicesId(Long id);

}
