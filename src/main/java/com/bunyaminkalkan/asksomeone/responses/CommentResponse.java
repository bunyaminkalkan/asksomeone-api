package com.bunyaminkalkan.asksomeone.responses;

import com.bunyaminkalkan.asksomeone.entities.Comment;
import lombok.Data;

@Data
public class CommentResponse {

    Long id;
    Long userId;
    String userName;
    String text;

    public CommentResponse(Comment entity) {
        this.id = entity.getId();
        this.userId = entity.getId();
        this.userName = entity.getUser().getUserName();
        this.text = entity.getText();
    }
}
