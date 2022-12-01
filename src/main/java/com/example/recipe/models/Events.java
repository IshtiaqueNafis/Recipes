package com.example.recipe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "events")

public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String eventName;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Lob
    @NonNull
    private String description;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;


    @OneToMany(mappedBy = "event",orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<EventAttends> users = new HashSet<>();

}
