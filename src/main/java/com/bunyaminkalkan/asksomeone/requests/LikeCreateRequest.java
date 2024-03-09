package com.bunyaminkalkan.asksomeone.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {

    Long id;
    Long postId;
    Long userId;
}
