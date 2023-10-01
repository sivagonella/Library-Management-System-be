package com.library.management.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "library_book")
public class LibraryBook {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Integer id;

    @Column(name = "book_name", unique = true)
    private String name;

    @Column(name = "number_of_books")
    private Integer quantity;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    public void addAuthor(Author author) {
        if(authors == null) {
            authors = new ArrayList<>();
        }
        authors.add(author);
    }
}
