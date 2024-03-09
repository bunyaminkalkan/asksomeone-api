package com.bunyaminkalkan.asksomeone.services;

import com.bunyaminkalkan.asksomeone.entities.Like;
import com.bunyaminkalkan.asksomeone.entities.Post;
import com.bunyaminkalkan.asksomeone.entities.User;
import com.bunyaminkalkan.asksomeone.repos.LikeRepository;
import com.bunyaminkalkan.asksomeone.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getAllLikes(Optional<Long> postId, Optional<Long> userId) {
        if(userId.isPresent()){
            return likeRepository.findByUserId(userId.get());
        }
        if(postId.isPresent()){
            return likeRepository.findByPostId(postId.get());
        }
        return likeRepository.findAll();
    }


    public Like createOneLike(LikeCreateRequest newLikeCreateRequest) {
        User user = userService.getOneUser(newLikeCreateRequest.getUserId());
        Post post = postService.getOnePostById(newLikeCreateRequest.getPostId());
        if (user == null || post == null)
            return null;
        Like toSave = new Like();
        toSave.setId(newLikeCreateRequest.getId());
        toSave.setUser(user);
        toSave.setPost(post);

        return likeRepository.save(toSave);
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }


    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}

