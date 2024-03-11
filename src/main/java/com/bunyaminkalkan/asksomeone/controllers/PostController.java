package com.bunyaminkalkan.asksomeone.controllers;

import com.bunyaminkalkan.asksomeone.entities.Post;
import com.bunyaminkalkan.asksomeone.requests.PostCreateRequest;
import com.bunyaminkalkan.asksomeone.requests.PostUpdateRequest;
import com.bunyaminkalkan.asksomeone.responses.PostResponse;
import com.bunyaminkalkan.asksomeone.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostCreateRequest){
        return postService.createOnePost(newPostCreateRequest);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdate){
        return postService.updateOnePostById(postId, postUpdate);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }
}
