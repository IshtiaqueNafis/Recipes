package com.example.recipe.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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

    @NonNull
    @Column(name = "recipe_type")
    private String type;

    @NonNull
    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @NonNull
    @Column(name = "photo")
    private String photo;


    @NonNull
    @Column(name = "is_private")
    private boolean availability = false;

    @NonNull
    @Column(name = "calories")
    private int calories;

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


    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private Set<Favourites> favourites = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;




}
