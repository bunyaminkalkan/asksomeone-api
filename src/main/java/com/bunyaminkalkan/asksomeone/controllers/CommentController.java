package com.bunyaminkalkan.asksomeone.controllers;

import com.bunyaminkalkan.asksomeone.entities.Comment;
import com.bunyaminkalkan.asksomeone.requests.CommentCreateRequest;
import com.bunyaminkalkan.asksomeone.requests.CommentUpdateRequest;
import com.bunyaminkalkan.asksomeone.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId){
        return commentService.getAllComments(postId, userId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest newCommentCreateRequest){
        return commentService.createOneComment(newCommentCreateRequest);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest updateComment){
        return commentService.updateOneCommentById(commentId, updateComment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }

}
