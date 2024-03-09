package com.bunyaminkalkan.asksomeone.repos;

import com.bunyaminkalkan.asksomeone.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUserId(Long aLong);

    List<Comment> findByPostId(Long aLong);

    List<Comment> findByUserIdAndPostId(Long aLong, Long aLong1);
}
