package com.soft.virtualstore.dto;

import com.soft.virtualstore.model.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CompanyDTO {

    private String link;
    private String image;
    private String name;

}
