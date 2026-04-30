package com.likelion.sns.repository;

import com.likelion.sns.entity.Comment;
import com.likelion.sns.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // 특정 게시글(Post)에 속한 댓글 목록 조회
    List<Comment> findAllByPost(Post post);
}