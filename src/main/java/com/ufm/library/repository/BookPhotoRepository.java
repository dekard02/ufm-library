package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.entity.BookPhoto;

public interface BookPhotoRepository extends JpaRepository<BookPhoto, Long> {

}
