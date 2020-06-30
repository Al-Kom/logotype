package com.softarexpractice.logotype.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "responses")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "field_id")
    private Field field;
}
