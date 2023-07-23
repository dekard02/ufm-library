package com.ufm.library.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ufm.library.entity.listener.BookListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({ AuditingEntityListener.class, BookListener.class })
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false, unique = true)
    private String title;

    @Column(length = 150, nullable = false, unique = true)
    private String slug;

    @Column
    private Integer totalPages;

    @Column(nullable = false, columnDefinition = "text")
    private String summary;

    @CreatedDate
    private LocalDateTime createdAt;

    private Boolean isDeleted;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<BookPhoto> photos;

    @OneToMany(mappedBy = "book")
    private List<LocationBook> locationBooks;

    @ManyToMany
    private List<Category> categories;

}
