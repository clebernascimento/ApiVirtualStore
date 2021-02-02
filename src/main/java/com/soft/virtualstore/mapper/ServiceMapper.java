package com.soft.virtualstore.mapper;

import com.soft.virtualstore.dto.ServiceDTO;
import com.soft.virtualstore.model.ServiceCard;

public class ServiceMapper {

    public static ServiceCard DtoToEntity(ServiceDTO serviceDTO){
        return new ServiceCard().setName(serviceDTO.getName()).setImage(serviceDTO.getImage());
    }

    public static ServiceDTO EntityToSto(ServiceCard service){
        return new ServiceDTO().setName(service.getName()).setImage(service.getImage());
    }
}
