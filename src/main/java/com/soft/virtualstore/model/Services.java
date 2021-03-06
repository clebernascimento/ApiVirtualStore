package com.soft.virtualstore.model;

import com.soft.virtualstore.repository.ServiceCompanyRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "tb_service")
@EntityListeners(AuditingEntityListener.class)
public class Services implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy =  "increment")
    @Id
    @Column(name = "ser_id")
    private Long id;

    @Column(name = "ser_image")
    private String image;

    @Column(name = "ser_name")
    private String name;

}
