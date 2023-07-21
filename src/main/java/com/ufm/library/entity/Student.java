package com.ufm.library.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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

    private String photo;

    private LocalDateTime dateOfBirth;

    private Boolean gender;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(mappedBy = "student")
    private Collection<BookLoanRecord> bookLoanRecords;

    @CreatedDate
    private LocalDateTime createdAt;

}
