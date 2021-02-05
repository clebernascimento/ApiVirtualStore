package com.soft.virtualstore.dto;

import com.soft.virtualstore.model.Services;
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
public class ServiceDTO {

    private String name;
    private String image;

}
