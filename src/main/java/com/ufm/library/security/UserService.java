package com.ufm.library.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ufm.library.repository.LibrarianRepository;
import com.ufm.library.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final LibrarianRepository librarianRepo;
    private final StudentRepository studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalLibrarian = librarianRepo.findByUsername(username);

        if (optionalLibrarian.isPresent()) {
            return new UserDetails(optionalLibrarian.get());
        } else {
            var optionalStudent = studentRepo.findById(username);
            if (optionalStudent.isEmpty()) {
                throw new UsernameNotFoundException("Tài khoản không hợp lệ!");
            }
            return new UserDetails(optionalStudent.get());
        }
    }

}
