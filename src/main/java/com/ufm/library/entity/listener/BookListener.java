package com.ufm.library.entity.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.github.slugify.Slugify;
import com.ufm.library.entity.Book;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookListener {
    private final Slugify slugify;

    @PrePersist
    @PreUpdate
    public void generateSlug(Book book) {
        var title = book.getTitle();
        book.setSlug(slugify.slugify(title));
    }
}
