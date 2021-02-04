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
@Table(name = "tb_company")
@EntityListeners(AuditingEntityListener.class)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "com_id")
    private int id;

    @Column(name = "com_image")
    private String image;

    @Column(name = "com_name")
    private String name;

    @Column(name = "com_link")
    private String link;
}
