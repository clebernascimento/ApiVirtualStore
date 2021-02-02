package com.soft.virtualstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "service")
@EntityListeners(AuditingEntityListener.class)
public class ServiceCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
    private int id;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;



}
