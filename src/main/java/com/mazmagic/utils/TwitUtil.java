package com.mazmagic.utils;

import com.mazmagic.model.Like;
import com.mazmagic.model.Twit;
import com.mazmagic.model.User;

public class TwitUtil {
    public final static boolean isLikedByUser(User reqUser, Twit twit){
        for (Like like: twit.getLikes()){
            if(like.getUser().getId().equals(reqUser.getId())){
                return true;
            }
        }
        return false;
    }
    public final static boolean isRetwitByUser(User reqUser,Twit twit){
        for (User user: twit.getRetwitUser()){
            if(user.getId().equals(reqUser.getId())){
                return true;
            }
        }
        return false;
    }
}
