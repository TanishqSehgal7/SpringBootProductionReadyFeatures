package com.example.springproductionready.springproductionready.controllers;


import com.example.springproductionready.springproductionready.entities.Post;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/audit")
public class AuditController {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @GetMapping(path = "/posts/{postId}")
    public List<Post> getPostRevisions(@PathVariable Long postId) {

        AuditReader reader = AuditReaderFactory
                .get(entityManagerFactory.createEntityManager());
        List<Number> revisionNumbers = reader.getRevisions(Post.class, postId);

        return revisionNumbers
                .stream()
                .map(revisionNumber -> reader.find(Post.class,postId,revisionNumber))
                .collect(Collectors.toList());
    }

}
