package com.bunyaminkalkan.asksomeone.services;

import com.bunyaminkalkan.asksomeone.entities.User;
import com.bunyaminkalkan.asksomeone.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAlluser() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save((newUser));
    }

    public User getOneUser(Long userId) {
        return userRepository.findById((userId)).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword((newUser.getPassword()));
            userRepository.save((foundUser));
            return foundUser;
        }
        else
            return null;
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
