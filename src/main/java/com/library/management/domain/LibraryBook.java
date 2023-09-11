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
@Data
@Table(name = "library_book")
public class LibraryBook {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Integer bookID;

    @Column(name = "book_name", unique = true)
    private String bookName;

    @Column(name = "number_of_books")
    private Integer numberOfBooks;

    @JsonManagedReference
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
