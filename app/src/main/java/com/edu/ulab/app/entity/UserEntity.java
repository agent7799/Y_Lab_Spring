package com.edu.ulab.app.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "person")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "title")
    private String title;

    @Column(name = "age")
    private int age;

}
