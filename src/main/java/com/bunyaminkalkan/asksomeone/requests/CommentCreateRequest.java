package com.bunyaminkalkan.asksomeone.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {

    Long id;
    String text;
    Long postId;
    Long userId;
}
