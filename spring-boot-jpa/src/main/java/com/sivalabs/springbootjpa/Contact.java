package com.sivalabs.springbootjpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@Setter
@Getter
@ToString
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Column(name="email", length = 100)
    private String emailId;

}
