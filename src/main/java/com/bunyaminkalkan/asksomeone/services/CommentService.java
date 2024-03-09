package com.bunyaminkalkan.asksomeone.services;

import com.bunyaminkalkan.asksomeone.entities.Comment;
import com.bunyaminkalkan.asksomeone.entities.Post;
import com.bunyaminkalkan.asksomeone.entities.User;
import com.bunyaminkalkan.asksomeone.repos.CommentRepository;
import com.bunyaminkalkan.asksomeone.requests.CommentCreateRequest;
import com.bunyaminkalkan.asksomeone.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllComments(Optional<Long> postId, Optional<Long> userId) {
        if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }
        if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }
        return commentRepository.findAll();
    }

    public Comment createOneComment(CommentCreateRequest newCommentCreateRequest) {
        User user = userService.getOneUser(newCommentCreateRequest.getUserId());
        Post post = postService.getOnePostById(newCommentCreateRequest.getPostId());
        if (user == null || post == null)
            return null;
        Comment toSave = new Comment();
        toSave.setId(newCommentCreateRequest.getId());
        toSave.setText(newCommentCreateRequest.getText());
        toSave.setPost(post);
        toSave.setUser(user);

        return commentRepository.save(toSave);
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentUpdate) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment toUpdate = comment.get();
            toUpdate.setText(commentUpdate.getText());
            commentRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
