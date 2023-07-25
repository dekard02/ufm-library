package com.ufm.library.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.ufm.library.dto.AuthorDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.mapper.AuthorMapper;
import com.ufm.library.entity.QAuthor;
import com.ufm.library.exception.ApplicationException;
import com.ufm.library.helper.ResponseBodyHelper;
import com.ufm.library.repository.AuthorRepository;
import com.ufm.library.service.AuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepo;
    private final AuthorMapper authorMapper;
    private final ResponseBodyHelper responseBodyHelper;

    @Override
    public ResponseBody getAllAuthors(Predicate predicate, Pageable pageable, String search) {
        var searchPredicate = QAuthor.author.fullname
                .contains(search)
                .and(predicate);
        var authorPage = authorRepo.findAll(searchPredicate, pageable);
        var authors = authorPage.getContent()
                .stream()
                .map(authorMapper::authorToAuthorDto)
                .collect(Collectors.toList());
        return responseBodyHelper.page(authorPage, "authors", authors);
    }

    @Override
    public ResponseBody getAuthor(Long id) {
        var authorDto = authorRepo.findById(id)
                .map(authorMapper::authorToAuthorDto)
                .orElseThrow(() -> new ApplicationException(
                        "Không tìm thấy tác giả", HttpStatus.NOT_FOUND));
        return responseBodyHelper.success("author", authorDto);
    }

    @Override
    public ResponseBody saveAuthor(AuthorDto authorDto) {
        var author = authorMapper.authorDtoToAuthor(authorDto);
        author.setId(null);
        authorRepo.save(author);
        return responseBodyHelper
                .success("author", authorMapper.authorToAuthorDto(author));
    }

    @Override
    public ResponseBody updateAuthor(Long id, AuthorDto authorDto) {
        if (!authorRepo.existsById(id)) {
            new ApplicationException("Không tìm thấy tác giả", HttpStatus.NOT_FOUND);
        }
        var author = authorMapper.authorDtoToAuthor(authorDto);
        author.setId(id);
        authorRepo.save(author);
        return responseBodyHelper
                .success("author", authorMapper.authorToAuthorDto(author));
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepo.findById(id)
                .ifPresentOrElse(authorRepo::delete,
                        () -> new ApplicationException("Không tìm thấy tác giả",
                                HttpStatus.NOT_FOUND));
    }
}
