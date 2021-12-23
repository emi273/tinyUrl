package com.example.tinyUrl.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
public class Redirect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @NaturalId
    @Column(unique = true, nullable = false)
    private String alias;

    @Column(nullable = false)
    private String url;


    public Redirect() {
    }

    public Redirect(final String alias, final String url) {
        this.alias = alias;
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public String getAlias() {
        return alias;
    }


    @Override
    public String toString() {
        return "Redirect{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


}
