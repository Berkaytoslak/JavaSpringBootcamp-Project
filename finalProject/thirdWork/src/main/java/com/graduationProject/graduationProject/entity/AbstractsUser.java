package com.graduationProject.graduationProject.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractsUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
