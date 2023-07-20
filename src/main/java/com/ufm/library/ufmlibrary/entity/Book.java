package com.ufm.library.ufmlibrary.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false, unique = true)
    private String title;

    @Column
    private Integer totalPages;

    @Column(nullable = false, columnDefinition = "text")
    private String summary;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private Collection<BookPhoto> photos;

    @OneToMany(mappedBy = "book")
    private Collection<LocationBook> locationBooks;

}
