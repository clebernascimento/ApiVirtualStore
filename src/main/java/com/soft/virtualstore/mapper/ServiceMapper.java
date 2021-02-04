package com.soft.virtualstore.mapper;

import com.soft.virtualstore.dto.ServiceDTO;
import com.soft.virtualstore.model.Services;

public class ServiceMapper {

    public static Services DtoToEntity(ServiceDTO serviceDTO){
        return new Services().setName(serviceDTO.getName()).setImage(serviceDTO.getImage());
    }

    public static ServiceDTO EntityToSto(Services service){
        return new ServiceDTO().setName(service.getName()).setImage(service.getImage());
    }
}
