package com.example.recipe.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipes")
@Builder
public class Recipe {
    public Recipe(Long id, @NonNull String name, @NonNull String description, User createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;


    @Column(name = "url")
    private String url;


    @Lob
    @NonNull
    @Column(name = "description")
    private String description;


    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;


    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

}
