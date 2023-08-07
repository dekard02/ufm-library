package com.ufm.library.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ufm.library.entity.Librarian;
import com.ufm.library.entity.Student;

import lombok.Data;

@Data
public class UserDetail implements UserDetails {

    private String username;
    private String password;
    private Object profile;
    private Collection<SimpleGrantedAuthority> grantedAuthorities;

    public UserDetail(Librarian librarian) {
        this.username = librarian.getUsername();
        this.password = librarian.getPassword();
        this.grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(
                librarian.getRole().getRoleCode()));
        this.profile = librarian;
    }

    public UserDetail(Student student) {
        this.username = student.getId();
        this.password = student.getPassword();
        this.grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("STUDENT"));
        this.profile = student;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
