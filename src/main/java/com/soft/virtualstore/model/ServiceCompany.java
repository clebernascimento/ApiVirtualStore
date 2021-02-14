package com.soft.virtualstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tb_service_company")
@EntityListeners(AuditingEntityListener.class)
public class ServiceCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Id
    @Column(name = "sec_id")
    private Long id;

    @JsonIgnore
    @JoinColumn(name = "sec_ser_id", referencedColumnName = "ser_id")
    @ManyToOne
    private Services services;

    @JoinColumn(name = "sec_com_id", referencedColumnName = "com_id")
    @ManyToOne
    private Company company;
}
