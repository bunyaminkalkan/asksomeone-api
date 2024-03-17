package com.bunyaminkalkan.asksomeone.services;

import com.bunyaminkalkan.asksomeone.entities.Comment;
import com.bunyaminkalkan.asksomeone.entities.Post;
import com.bunyaminkalkan.asksomeone.entities.User;
import com.bunyaminkalkan.asksomeone.repos.CommentRepository;
import com.bunyaminkalkan.asksomeone.requests.CommentCreateRequest;
import com.bunyaminkalkan.asksomeone.requests.CommentUpdateRequest;
import com.bunyaminkalkan.asksomeone.responses.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<CommentResponse> getAllComments(Optional<Long> postId, Optional<Long> userId) {
        List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()){
            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if(userId.isPresent()){
            comments = commentRepository.findByUserId(userId.get());
        } else if(postId.isPresent()){
            comments = commentRepository.findByPostId(postId.get());
        } else
            comments = commentRepository.findAll();

        return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }

    public Comment createOneComment(CommentCreateRequest newCommentCreateRequest) {
        User user = userService.getOneUserById(newCommentCreateRequest.getUserId());
        Post post = postService.getOnePostById(newCommentCreateRequest.getPostId());
        if (user != null && post != null) {
            Comment toSave = new Comment();
            toSave.setId(newCommentCreateRequest.getId());
            toSave.setText(newCommentCreateRequest.getText());
            toSave.setPost(post);
            toSave.setUser(user);
            toSave.setCreateDate(new Date());
            return commentRepository.save(toSave);
        }
        return null;
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
