package com.mazmagic.dto.mapper;

import com.mazmagic.dto.TwitDto;
import com.mazmagic.dto.UserDto;
import com.mazmagic.model.Twit;
import com.mazmagic.model.User;
import com.mazmagic.utils.TwitUtil;

import java.util.ArrayList;
import java.util.List;

public class TwitDtoMapper {
    public static TwitDto TwitToTwitDto(Twit twit, User reqUser){


        UserDto user=UserDtoMapper.UsertoUserDto(twit.getUser());
        boolean isLiked= TwitUtil.isLikedByUser(reqUser,twit);
        boolean isReteit=TwitUtil.isRetwitByUser(reqUser, twit);
        List<Long> reTwitUserId=new ArrayList<>();

        for (User u: twit.getRetwitUser()){
            reTwitUserId.add(u.getId());
        }

        TwitDto twitDto=new TwitDto();
        twitDto.setId(twit.getId());
        twitDto.setContent(twit.getContent());
        twitDto.setCreatedAt(twit.getCreatedAt());
        twitDto.setImage(twitDto.getImage());
        twitDto.setTotalLikes(twit.getLikes().size());
        twitDto.setTotalReplies(twit.getReplyTwits().size());
        twitDto.setTotalRetweets(twit.getRetwitUser().size());
        twitDto.setUser(user);
        twitDto.setLiked(isLiked);
        twitDto.setReTwit(isReteit);
        twitDto.setRetwitUserId(reTwitUserId);
        twitDto.setReplyTwits(toTwitDtos(twit.getReplyTwits(),reqUser));
        twitDto.setVideo(twitDto.getVideo());


        return twitDto;
    }

    public static List<TwitDto> toTwitDtos(List<Twit> replyTwits, User reqUser) {
        List<TwitDto> twitDtos=new ArrayList<>();
        for (Twit twit:replyTwits){
            TwitDto t=toReplyTwitDto(twit,reqUser);
            twitDtos.add(t);
        }
        return twitDtos;
    }

    public  static TwitDto toReplyTwitDto(Twit twit, User reqUser) {
        UserDto user=UserDtoMapper.UsertoUserDto(twit.getUser());
        boolean isLiked= TwitUtil.isLikedByUser(reqUser,twit);
        boolean isReteit=TwitUtil.isRetwitByUser(reqUser, twit);
        List<Long> reTwitUserId=new ArrayList<>();

        for (User u: twit.getRetwitUser()){
            reTwitUserId.add(u.getId());
        }

        TwitDto twitDto=new TwitDto();
        twitDto.setId(twit.getId());
        twitDto.setContent(twit.getContent());
        twitDto.setCreatedAt(twit.getCreatedAt());
        twitDto.setImage(twitDto.getImage());
        twitDto.setTotalLikes(twit.getLikes().size());
        twitDto.setTotalReplies(twit.getReplyTwits().size());
        twitDto.setTotalRetweets(twit.getRetwitUser().size());
        twitDto.setUser(user);
        twitDto.setLiked(isLiked);
        twitDto.setReTwit(isReteit);
        twitDto.setRetwitUserId(reTwitUserId);
        twitDto.setVideo(twitDto.getVideo());


        return twitDto;

    }

}
