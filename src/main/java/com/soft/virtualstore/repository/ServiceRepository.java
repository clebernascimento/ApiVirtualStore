package com.soft.virtualstore.repository;

import com.soft.virtualstore.model.ServiceCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceCard, Integer> {

}
