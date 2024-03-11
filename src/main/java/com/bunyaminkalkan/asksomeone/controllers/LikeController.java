package com.bunyaminkalkan.asksomeone.controllers;

import com.bunyaminkalkan.asksomeone.entities.Like;
import com.bunyaminkalkan.asksomeone.requests.LikeCreateRequest;
import com.bunyaminkalkan.asksomeone.responses.LikeResponse;
import com.bunyaminkalkan.asksomeone.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId){
        return likeService.getAllLikes(postId, userId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest newLikeCreateRequest){
        return likeService.createOneLike(newLikeCreateRequest);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }
}
