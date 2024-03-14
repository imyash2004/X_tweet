package com.mazmagic.service;

import com.mazmagic.model.User;
import com.mazmagic.response.UserException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    public User findUserById(Long userId)throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException;
    public User updateUser(Long userId,User user) throws UserException;
    public User followUser(Long userId,User user) throws UserException;
    public List<User> searchUser(String query);

}
