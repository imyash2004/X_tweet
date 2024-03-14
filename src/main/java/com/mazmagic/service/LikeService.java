package com.mazmagic.service;

import com.mazmagic.model.Like;
import com.mazmagic.model.User;
import com.mazmagic.response.LikeException;
import com.mazmagic.response.TwitException;
import com.mazmagic.response.UserException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LikeService {
    public Like likeTwit(Long twitId, User user) throws UserException, LikeException, TwitException;
    public Like unLikeTwit(Long twitId, User user) throws UserException, LikeException, TwitException;
    public List<Like> getAllLikes(Long twitId) throws TwitException,LikeException;

}
