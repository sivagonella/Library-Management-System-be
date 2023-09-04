package com.library.management.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DynamicUpdate
@Data
public class LibraryBook {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "book_id", nullable = false)
    private Integer bookID;

    @Column(name = "book_name", unique = true)
    private String bookName;

    @Column(name = "number_of_books")
    private Integer numberOfBooks;

    @JsonManagedReference
    @OneToMany(mappedBy = "libraryBook", cascade = CascadeType.ALL)
    private List<Author> authorNames = new ArrayList<>();
}
