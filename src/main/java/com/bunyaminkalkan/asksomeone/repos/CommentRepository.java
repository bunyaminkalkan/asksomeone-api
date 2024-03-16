package com.bunyaminkalkan.asksomeone.repos;

import com.bunyaminkalkan.asksomeone.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUserId(Long aLong);

    List<Comment> findByPostId(Long aLong);

    List<Comment> findByUserIdAndPostId(Long aLong, Long aLong1);

    @Query(value = "SELECT 'commented on' , c.post_id, u.avatar, u.user_name FROM "
            + "comment c LEFT JOIN user u on u.id = c.user_id "
            + "WHERE c.post_id in :postIds LIMIT 5", nativeQuery = true)
    List<Object> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);
}
