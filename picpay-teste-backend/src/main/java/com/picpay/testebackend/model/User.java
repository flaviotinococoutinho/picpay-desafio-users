package com.picpay.testebackend.model;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Document(indexName = "users", type = "user")
public class User {

    @Getter
    @Setter
    @Id
    private String id;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String username;

}
