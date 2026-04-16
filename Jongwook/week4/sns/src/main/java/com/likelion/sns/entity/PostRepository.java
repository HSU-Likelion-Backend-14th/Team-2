package com.likelion.sns.entity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.content LIKE %:keyword%")
    List<Post> findByContentContaining(@Param("keyword") String keyword);

    List<Post> findTop5ByUserOrderByCreatedAtDesc(User user);

    List<Post> findAllByOrderByViewCountDesc();
}
