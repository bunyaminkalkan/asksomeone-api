package com.bunyaminkalkan.asksomeone.repos;

import com.bunyaminkalkan.asksomeone.entities.Comment;
import com.bunyaminkalkan.asksomeone.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUserId(Long aLong);
    List<Like> findByPostId(Long aLong);

    List<Like> findByUserIdAndPostId(Long aLong, Long aLong1);

    @Query(value = "SELECT 'liked', l.post_id, u.avatar, u.user_name FROM "
            + "p_like l left join user u on u.id = l.user_id "
            + "WHERE l.post_id in :postIds LIMIT 5", nativeQuery = true)
    List<Object> findUserLikesByPostId(@Param("postIds") List<Long> postIds);
}
