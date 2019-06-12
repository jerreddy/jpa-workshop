package com.sivalabs.springbootjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaApplication.class, args);
    }

    @Autowired
    ContactService contactService;

    @Override
    public void run(String... args) throws Exception {
        Contact abcd = contactService.createContact("abcd", "abcd@gmail.com");
        System.out.println(abcd);
        List<Contact> contacts = contactService.allContacts();
        System.out.println(contacts);
    }
}
