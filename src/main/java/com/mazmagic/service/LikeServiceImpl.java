package com.mazmagic.service;

import com.mazmagic.model.Like;
import com.mazmagic.model.Twit;
import com.mazmagic.model.User;
import com.mazmagic.repositories.LikeRepo;
import com.mazmagic.repositories.TwitRepo;
import com.mazmagic.response.LikeException;
import com.mazmagic.response.TwitException;
import com.mazmagic.response.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class LikeServiceImpl implements LikeService{


    @Autowired
    private TwitService twitService;
    @Autowired
    private LikeRepo likeRepo;
    @Autowired
    private TwitRepo twitRepo;

    @Override
    public Like likeTwit(Long twitId, User user) throws UserException, LikeException, TwitException {
        Like isLikeExist=likeRepo.isLikeExist(user.getId(),twitId);
        if(isLikeExist!=null){
            likeRepo.deleteById(isLikeExist.getId());
            return isLikeExist;
        }
        Like like=new Like();
        Twit twit=twitService.findById(twitId);
        like.setTwit(twit);
        like.setUser(user);
        Like savedLike=likeRepo.save(like);
        twit.getLikes().add(like);
        user.getLikes().add(like);
        return savedLike;


    }

    @Override
    public Like unLikeTwit(Long twitId, User user) throws UserException, LikeException, TwitException {
        Like like=likeRepo.findById(twitId).orElseThrow(()->new LikeException("Like Not Found"));
    if(like.getUser().getId().equals(user.getId())){
        throw new UserException("somthing went wrong...");
    }
        likeRepo.deleteById(like.getId());
        return like;
    }

    @Override
    public List<Like> getAllLikes(Long twitId) throws TwitException, LikeException {
        List<Like> likes=likeRepo.findByTwit(twitId);
        return likes;
    }
}
