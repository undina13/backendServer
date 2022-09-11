package com.undina.backendserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "advertisements")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "contact", nullable = false)
    private String contact;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User owner;

    @Column(name = "image_file_name")
    private String imageFileName;
}
