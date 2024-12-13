package com.example.springproductionready.springproductionready.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Post extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String title;

//    @NotAudited
    private String description;

    /*
    *  When an entity is persisted, or updated, the AuditingEntityListener
    * triggers and performs the following actions:
      1. PrePersist
      2. PreUpdate
      AuditorAware interface provides the information about the current user.
    * */

    @PrePersist
    void beforeSave() {

    }

    @PreUpdate
    void beforeUpdate() {

    }

    @PreRemove
    void beforeDelete() {

    }

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;
}
