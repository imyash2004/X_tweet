package com.mazmagic.service;

import com.mazmagic.model.Twit;
import com.mazmagic.model.User;
import com.mazmagic.request.TwitReplyRequest;
import com.mazmagic.response.TwitException;
import com.mazmagic.response.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TwitService {
    public Twit createTwit(Twit req, User user)throws UserException;
    public List<Twit> findAllTwit();
    public Twit reTwit(Long twitId,User user)throws TwitException,UserException;

    public Twit findById(Long twitId) throws TwitException;
    public void deletTwitById(Long twitId,Long UserId)throws TwitException,UserException;
    public Twit removeFromRetwit(Long twitId,User user)throws TwitException,UserException;
    public Twit createdReply(TwitReplyRequest req,User user)throws TwitException,UserException;

    public List<Twit> getUserTwit(User user);
    public List<Twit> findByLikesContainsUser(User user);



}
