package com.ufm.library.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private RoleName role;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Collection<Librarian> librarians;

    public static enum RoleName {
        MANAGER {
            @Override
            public String toString() {
                return "MANAGER";
            }
        },
        LIBRARIAN {
            @Override
            public String toString() {
                return "LIBRARIAN";
            }
        },
    }
}
