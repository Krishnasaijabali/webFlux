package com.example.webFlux.model;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;


@Data
public class Register {

    @Id
    String email;
    @Column
    String name;
    @Column
    String password;


}
