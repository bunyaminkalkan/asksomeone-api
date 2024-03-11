package com.bunyaminkalkan.asksomeone.services;

import com.bunyaminkalkan.asksomeone.entities.Like;
import com.bunyaminkalkan.asksomeone.entities.Post;
import com.bunyaminkalkan.asksomeone.entities.User;
import com.bunyaminkalkan.asksomeone.repos.LikeRepository;
import com.bunyaminkalkan.asksomeone.requests.LikeCreateRequest;
import com.bunyaminkalkan.asksomeone.responses.LikeResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    @Lazy
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, @Lazy PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLikes(Optional<Long> postId, Optional<Long> userId) {
        List<Like> list;

        if(userId.isPresent() && postId.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()){
            list = likeRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            list = likeRepository.findByPostId(postId.get());
        }else {
        list = likeRepository.findAll();
        }
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }


    public Like createOneLike(LikeCreateRequest newLikeCreateRequest) {
        User user = userService.getOneUserById(newLikeCreateRequest.getUserId());
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

