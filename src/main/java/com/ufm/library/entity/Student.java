package com.ufm.library.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Student {
    @Id
    @Column(columnDefinition = "char(10)")
    private String id;

    @Column(length = 150, nullable = false)
    private String fullname;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(columnDefinition = "char(15)", nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "char(60)", nullable = false)
    private String password;

    @Column(length = 150, unique = true)
    private String email;

    private String photo;

    private LocalDateTime dateOfBirth;

    private Boolean gender;

    private Boolean isDeleted;

    @CreatedDate
    private LocalDateTime createdAt;

}
