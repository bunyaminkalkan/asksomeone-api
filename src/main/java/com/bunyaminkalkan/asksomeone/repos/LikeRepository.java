package com.bunyaminkalkan.asksomeone.repos;

import com.bunyaminkalkan.asksomeone.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUserId(Long aLong);
    List<Like> findByPostId(Long aLong);

    List<Like> findByUserIdAndPostId(Long aLong, Long aLong1);
}
