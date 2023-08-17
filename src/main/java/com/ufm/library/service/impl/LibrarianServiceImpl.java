package com.ufm.library.service.impl;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.types.Predicate;
import com.ufm.library.constant.Constants;
import com.ufm.library.constant.StorageContants;
import com.ufm.library.dto.LibrarianDto;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.mapper.LibrarianMapper;
import com.ufm.library.entity.Librarian;
import com.ufm.library.entity.QLibrarian;
import com.ufm.library.exception.NotFoundException;
import com.ufm.library.helper.FilenameHelper;
import com.ufm.library.helper.ResponseBodyHelper;
import com.ufm.library.repository.LibrarianRepository;
import com.ufm.library.repository.RoleRepository;
import com.ufm.library.service.LibrarianService;
import com.ufm.library.service.StorageService;

import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {

    private final RoleRepository roleRepo;
    private final LibrarianRepository librarianRepo;
    private final LibrarianMapper librarianMapper;
    private final StorageService storageService;
    private final ResponseBodyHelper responseBodyHelper;
    private final FilenameHelper filenameHelper;

    @Override
    public ResponseBody getAllLibrarians(Predicate predicate, Pageable pageable, String search) {
        var searchPredicate = QLibrarian.librarian.fullname
                .concat(QLibrarian.librarian.phoneNumber)
                .concat(QLibrarian.librarian.citizenId)
                .concat(QLibrarian.librarian.username)
                .containsIgnoreCase(search)
                .and(predicate);
        var librarianPage = librarianRepo.findAll(searchPredicate, pageable);
        var librarians = librarianPage.getContent()
                .stream()
                .map(librarianMapper::librarianToLibrarianResDto)
                .collect(Collectors.toList());
        return responseBodyHelper.page(librarianPage, "librarians", librarians);
    }

    @Override
    public ResponseBody getLibrarian(Long id) {
        var librarianDto = librarianRepo.findById(id)
                .map(librarianMapper::librarianToLibrarianResDto)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sinh viên với mã " + id));
        return responseBodyHelper.success("librarian", librarianDto);
    }

    private void saveImage(Librarian librarian, MultipartFile multipartFile) {
        var photo = StorageContants.LIBRARIAN_DEFAULT_IMAGE;
        try {
            var extension = filenameHelper.getMultipartFileExtension(multipartFile);
            var photoName = UUID.randomUUID().toString() + "." + extension;
            var inputStream = multipartFile.getInputStream();
            photo = storageService.store(inputStream, photoName,
                    StorageContants.LIBRARIAN_IMAGES_FOLDER);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        librarian.setPhoto(photo);
    }

    @Override
    public ResponseBody saveLibrarian(LibrarianDto.Request librarianDto) {
        var librarian = librarianMapper.librarianReqDtoToLibrarian(librarianDto, roleRepo);

        librarian.setId(null);
        librarian.setPassword(Constants.ENCODED_DEFAULT_PASSWORD);

        if (librarianDto.getPhoto() != null) {
            saveImage(librarian, librarianDto.getPhoto());
        } else {
            librarian.setPhoto(StorageContants.LIBRARIAN_DEFAULT_IMAGE);
        }

        librarianRepo.save(librarian);
        return responseBodyHelper
                .success("librarian",
                        librarianMapper.librarianToLibrarianResDto(librarian));
    }

    @Override
    public ResponseBody updateLibrarian(Long id, LibrarianDto.Request librarianDto) {
        var librarian = librarianRepo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Không tìm thấy thủ thư với mã " + id));

        var updateLibrarian = librarianMapper.librarianReqDtoToLibrarian(librarianDto, roleRepo);
        BeanUtils.copyProperties(updateLibrarian, librarian, "password", "photo", "createdAt");
        librarian.setId(id);

        if (librarianDto.getPhoto() != null) {
            var oldPhoto = librarian.getPhoto();
            saveImage(librarian, librarianDto.getPhoto());
            if (oldPhoto != StorageContants.LIBRARIAN_DEFAULT_IMAGE) {
                storageService.delete(oldPhoto);
            }
        }

        librarianRepo.save(librarian);
        return responseBodyHelper
                .success("librarian",
                        librarianMapper.librarianToLibrarianResDto(librarian));
    }

    @Override
    public void deleteLibrarian(Long id) {
        librarianRepo.findById(id).ifPresentOrElse(
                (librarian) -> {
                    librarian.setActive(false);
                    librarianRepo.save(librarian);
                },
                () -> {
                    throw new NotFoundException("Không tìm thấy thủ thư với mã " + id);
                });
    }

}
