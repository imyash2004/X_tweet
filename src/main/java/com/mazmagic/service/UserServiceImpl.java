package com.mazmagic.service;

import com.mazmagic.config.JwtProvider;
import com.mazmagic.model.User;
import com.mazmagic.repositories.UserRepo;
import com.mazmagic.response.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {
       User user=this.userRepo.findById(userId).orElseThrow(()->new UserException("User not found"));
        return user;
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email=jwtProvider.getEmailFromToken(jwt);
        System.out.println(email+"email");
        User user=this.userRepo.findUserByEmail(email);
        if (user==null){
            throw new UserException("User not exist....");
        }
        return user;
    }

    @Override
    public User updateUser(Long userId, User user) throws UserException {
       return this.userRepo.save(user);
//        User user=findUserById(userid);
//
//        if(req.getFullName()!= null) {
//            user.setFullName(req.getFullName());
//        }
//        if(req.getImage()!=null) {
//            user.setImage(req.getImage());
//        }
//        if(req.getBackgroundImage()!=null) {
//            user.setBackgroundImage(req.getBackgroundImage());
//        }
//        if(req.getBirthDate()!=null) {
//            user.setBirthDate(req.getBirthDate());
//        }
//        if(req.getLocation()!=null) {
//            user.setLocation(req.getLocation());
//        }
//        if(req.getBio()!=null) {
//            user.setBio(req.getBio());
//        }
//        if(req.getWebsite()!=null) {
//            user.setWebsite(req.getWebsite());
//        }
//
//        return userRepository.save(user);


    }

    @Override
    public User followUser(Long userId, User user) throws UserException {
        User followToUser=findUserById(userId);
        if(user.getFollowings().contains(followToUser)  && followToUser.getFollowers().contains(user)){
            user.getFollowings().remove(followToUser);
            followToUser.getFollowers().remove(user);
        }
        else{
            user.getFollowings().add(followToUser);
            followToUser.getFollowers().add(user);
        }
        this.userRepo.save(followToUser);
        this.userRepo.save(user);
        return followToUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return this.userRepo.searchUser(query);
    }
}
