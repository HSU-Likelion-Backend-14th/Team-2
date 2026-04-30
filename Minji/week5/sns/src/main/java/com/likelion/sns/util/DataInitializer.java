package com.likelion.sns.util;

import com.likelion.sns.entity.Post;
import com.likelion.sns.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PostRepository postRepository;

    @Override
    public void run(String... args) {
        if (postRepository.count() == 0) { // 게시글이 하나도 없을 때만 실행
            Post post = new Post();
            post.setContent("과제 테스트용 게시글입니다.");
            post.setViewCount(0);
            postRepository.save(post);
            System.out.println("=== 테스트용 1번 게시글이 생성되었습니다! ===");
        }
    }
}