package com.ufm.library.controller.impl;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.ufm.library.controller.AuthorController;
import com.ufm.library.dto.AuthorDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.entity.Author;
import com.ufm.library.service.AuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/authors")
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseBody> getAllAuthors(
            @QuerydslPredicate(root = Author.class) Predicate predicate,
            @PageableDefault Pageable pageable,
            @RequestParam(defaultValue = "", required = false) String search) {
        var response = authorService.getAllAuthors(predicate, pageable, search);
        return ok(response);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ResponseBody> getAuthor(@PathVariable Long id) {
        var response = authorService.getAuthor(id);
        return ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseBody> createAuthor(@RequestBody AuthorDto authorDto) {
        var response = authorService.saveAuthor(authorDto);
        return status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<ResponseBody> updateAuthor(@PathVariable Long id,
            @RequestBody AuthorDto authorDto) {
        var response = authorService.updateAuthor(id, authorDto);
        return ok(response);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseBody> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return noContent().build();
    }

}