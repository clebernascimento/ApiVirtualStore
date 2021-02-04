package com.soft.virtualstore.dto;

import com.soft.virtualstore.model.ServiceCompany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ServiceCompanyDTO {

    private int sec_id;
    private int sec_ser_id;
    private int sec_com_id;

}
