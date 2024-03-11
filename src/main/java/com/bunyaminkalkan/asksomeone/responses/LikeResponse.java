package com.bunyaminkalkan.asksomeone.responses;

import com.bunyaminkalkan.asksomeone.entities.Like;
import lombok.Data;

import java.util.List;

@Data
public class LikeResponse {

    Long id;
    Long userId;
    Long postId;

    public LikeResponse(Like entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}
