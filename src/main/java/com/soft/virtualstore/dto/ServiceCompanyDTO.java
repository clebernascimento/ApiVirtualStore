package com.soft.virtualstore.dto;

import com.soft.virtualstore.model.Company;
import com.soft.virtualstore.model.Services;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
public class ServiceCompanyDTO {

    private Long serviceId;
    private Long companyId;

}
