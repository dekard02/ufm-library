package com.ufm.library.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ufm.library.dto.api.ResponseBody;
import com.ufm.library.dto.auth.ChangePasswordDto;
import com.ufm.library.dto.auth.SignInDto;
import com.ufm.library.dto.mapper.LibrarianMapper;
import com.ufm.library.dto.mapper.StudentMapper;
import com.ufm.library.entity.Librarian;
import com.ufm.library.entity.Student;
import com.ufm.library.exception.ApplicationException;
import com.ufm.library.helper.ResponseBodyHelper;
import com.ufm.library.repository.LibrarianRepository;
import com.ufm.library.repository.StudentRepository;
import com.ufm.library.security.UserDetails;
import com.ufm.library.service.AuthService;
import com.ufm.library.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ResponseBodyHelper responseBodyHelper;
    private final LibrarianRepository librarianRepo;
    private final StudentRepository studentRepo;
    private final LibrarianMapper librarianMapper;
    private final StudentMapper studentMapper;

    private Object getProfileDto() {
        var userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var profile = userDetail.getProfile();

        if (profile instanceof Librarian) {
            return librarianMapper.librarianToLibrarianResDto((Librarian) profile);
        } else {
            return studentMapper.studentToStudentResDto((Student) profile);
        }
    }

    @Override
    public ResponseBody getProfile() {
        var profile = getProfileDto();
        return responseBodyHelper.success("profile", profile);
    }

    @Override
    public ResponseBody signIn(SignInDto signInDto, HttpServletResponse response) {
        var username = signInDto.getUsername().trim();
        var password = signInDto.getPassword();
        var token = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(token);

        var accessToken = jwtService.createAccessToken(username);
        var refreshToken = jwtService.createRefreshToken(username);
        var profile = getProfileDto();

        response.addCookie(new Cookie("refreshToken", refreshToken));
        return responseBodyHelper.success("accessToken", accessToken)
                .addField("profile", profile);
    }

    @Override
    public ResponseBody changePassword(Authentication authentication,
            ChangePasswordDto changePasswordDto,
            String accessToken,
            String refreshToken) {
        var userDetail = (UserDetails) authentication.getPrincipal();
        var passwordMatch = passwordEncoder.matches(
                changePasswordDto.getCurrentPassword(),
                userDetail.getPassword());
        if (!passwordMatch) {
            throw new ApplicationException("Mật khẩu hiện tại không đúng",
                    HttpStatus.UNAUTHORIZED);
        }

        var newPassword = passwordEncoder.encode(changePasswordDto.getPassword());
        if (userDetail.getProfile() instanceof Librarian) {
            var librarian = (Librarian) userDetail.getProfile();
            librarian.setPassword(newPassword);
            librarianRepo.save(librarian);
        } else if (userDetail.getProfile() instanceof Student) {
            var student = (Student) userDetail.getProfile();
            student.setPassword(newPassword);
            studentRepo.save(student);
        }

        jwtService.removeJwtFromCache(refreshToken);
        jwtService.removeJwtFromCache(accessToken);

        return responseBodyHelper.success("message", "Đổi mật khẩu thành công");
    }

    @Override
    public ResponseBody signOut(String accessToken, String refreshToken) {
        jwtService.removeJwtFromCache(refreshToken);
        jwtService.removeJwtFromCache(accessToken);

        return responseBodyHelper.success("message", "Đăng xuất thành công");
    }

    @Override
    public ResponseBody refreshToken(String refreshToken) {
        if (refreshToken.equals("")) {
            throw new ApplicationException("Hãy đăng nhập để tiếp tục",
                    HttpStatus.UNAUTHORIZED);
        }

        try {
            var decoded = jwtService.verifyAndDecodeRefreshToken(refreshToken);
            var username = decoded.getSubject();
            var accessToken = jwtService.createAccessToken(username);
            return responseBodyHelper.success("accessToken", accessToken);
        } catch (TokenExpiredException e) {
            throw new ApplicationException("Refresh token của bạn đã hết hạn. Hãy đăng nhập để tiếp tục",
                    HttpStatus.UNAUTHORIZED);
        }
    }

}
