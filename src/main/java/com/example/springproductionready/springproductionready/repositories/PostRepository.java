package com.example.springproductionready.springproductionready.repositories;

import com.example.springproductionready.springproductionready.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

}
