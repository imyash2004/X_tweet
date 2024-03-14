package com.mazmagic.service;

import com.mazmagic.model.Twit;
import com.mazmagic.model.User;
import com.mazmagic.repositories.TwitRepo;
import com.mazmagic.request.TwitReplyRequest;
import com.mazmagic.response.TwitException;
import com.mazmagic.response.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public class TwitServiceImpl implements TwitService{
    @Autowired
    private TwitRepo twitRepo;
    @Override
    public Twit createTwit(Twit req, User user) throws UserException {
        Twit twit=new Twit();
        twit.setContent(req.getContent());
        twit.setCreatedAt(LocalDateTime.now());
        twit.setUser(user);
        twit.setImage(req.getImage());
        twit.setTwit(true);
        twit.setReply(false);
        twit.setVideo(req.getVideo());


        return this.twitRepo.save(twit);
    }

    @Override
    public List<Twit> findAllTwit() {

        return this.twitRepo.findAllByIsTwitTrueOrderByCreatedAtDesc();
    }

    @Override
    public Twit reTwit(Long twitId, User user) throws TwitException, UserException {
        Twit twit=findById(twitId);
        if(twit.getRetwitUser().contains(user)){
            twit.getRetwitUser().remove(user);
        }
        else{
            twit.getRetwitUser().add(user);
        }
        return twit;


    }

    @Override
    public Twit findById(Long twitId) throws TwitException {
        Twit twit=twitRepo.findById(twitId).orElseThrow(()->new TwitException("twit does not exist wu=ith this id"));
        return twit;
    }

    @Override
    public void deletTwitById(Long twitId, Long UserId) throws TwitException, UserException {
        Twit twit=findById(twitId);
        if (!UserId.equals(twit.getUser().getId())){
            throw new UserException("you can't delte another user's twit");
        }
        twitRepo.deleteById(twit.getId());


    }

    @Override
    public Twit removeFromRetwit(Long twitId, User user) throws TwitException, UserException {
        Twit twit=findById(twitId);

        twit.getRetwitUser().remove(user);

        return twitRepo.save(twit);    }

    @Override
    public Twit createdReply(TwitReplyRequest req, User user) throws TwitException, UserException {
        Twit twit=new Twit();
        Twit replyFor=findById(req.getTwitId());
        twit.setContent(req.getContent());
        twit.setCreatedAt(LocalDateTime.now());
        twit.setUser(user);
        twit.setImage(req.getImage());
        twit.setTwit(false);
        twit.setReply(true);
        twit.setReplyFor(replyFor);


        Twit savedtwit=twitRepo.save(twit);
        replyFor.getReplyTwits().add(savedtwit);
        twitRepo.save(replyFor);
        return replyFor;
    }

    @Override
    public List<Twit> getUserTwit(User user) {
        return twitRepo.findByRetwitUserContainsOrUser_IdAndIsTwitTrueOrderByCreatedAtDesc(user, user.getId());
    }

    @Override
    public List<Twit> findByLikesContainsUser(User user) {
        return twitRepo.findByLikesUser_id(user.getId());
    }
}
