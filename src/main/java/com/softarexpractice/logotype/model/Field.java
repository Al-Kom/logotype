package com.softarexpractice.logotype.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String label;

    @Enumerated(EnumType.STRING)
    private FieldType type;

    private boolean required;

    @Column(name = "is_active")
    private boolean isActive;

    @ElementCollection
    private List<String> options;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<Response> responses;
}
