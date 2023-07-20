package com.ufm.library.ufmlibrary.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String fullname;

    @Column(length = 100, unique = true, nullable = false)
    private String username;

    @Column(columnDefinition = "char(60)", nullable = false)
    private String password;

    @Column(columnDefinition = "char(15)")
    private String citizenId;

    private String photo;

    private LocalDateTime dateOfBirth;

    @Column(columnDefinition = "char(15)")
    private String phoneNumber;

    @ManyToOne
    private Role role;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(mappedBy = "librarian")
    private Collection<BookLoanRecord> bookLoanRecords;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(mappedBy = "librarian")
    private Collection<BookReturnRecord> bookReturnRecords;

    @CreatedDate
    private LocalDateTime createdAt;

}
