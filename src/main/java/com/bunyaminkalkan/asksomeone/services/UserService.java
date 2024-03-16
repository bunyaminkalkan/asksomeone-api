package com.bunyaminkalkan.asksomeone.services;

import com.bunyaminkalkan.asksomeone.entities.Comment;
import com.bunyaminkalkan.asksomeone.entities.Like;
import com.bunyaminkalkan.asksomeone.entities.User;
import com.bunyaminkalkan.asksomeone.repos.CommentRepository;
import com.bunyaminkalkan.asksomeone.repos.LikeRepository;
import com.bunyaminkalkan.asksomeone.repos.PostRepository;
import com.bunyaminkalkan.asksomeone.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private LikeRepository likeRepository;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public UserService(UserRepository userRepository, LikeRepository likeRepository, CommentRepository commentRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save((newUser));
    }

    public User getOneUserById(Long userId) {
        return userRepository.findById((userId)).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            if(newUser.getUserName() != null)
                foundUser.setUserName(newUser.getUserName());

            if(newUser.getPassword() != null)
                foundUser.setPassword(newUser.getPassword());

            if(newUser.getAvatar() != foundUser.getAvatar())
                foundUser.setAvatar(newUser.getAvatar());
            userRepository.save((foundUser));
            return foundUser;
        }
        else
            return null;
    }

    public void deleteOneUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<Object> getUserActivity(Long userId) {
        List<Long> postIds = postRepository.findTopByUserId(userId);
        if(postIds.isEmpty())
            return null;
        List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
        List<Object> likes =likeRepository.findUserLikesByPostId(postIds);
        List<Object> result = new ArrayList<>();
        result.addAll(comments);
        result.addAll(likes);
        return result;
    }
}
