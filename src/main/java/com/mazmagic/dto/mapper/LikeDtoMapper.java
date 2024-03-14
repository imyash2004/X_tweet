package com.mazmagic.dto.mapper;

import com.mazmagic.dto.LikeDto;
import com.mazmagic.dto.TwitDto;
import com.mazmagic.dto.UserDto;
import com.mazmagic.model.Like;
import com.mazmagic.model.User;

import java.util.ArrayList;
import java.util.List;

public class LikeDtoMapper {
    public static LikeDto toLikeDtoMapper(Like like, User req_user){
        UserDto userDto=UserDtoMapper.UsertoUserDto(like.getUser());
        UserDto reqUserDto=UserDtoMapper.UsertoUserDto(req_user);
        TwitDto twitDto=TwitDtoMapper.TwitToTwitDto(like.getTwit(),req_user);



        LikeDto likeDto=new LikeDto();
        likeDto.setId(like.getId());
        likeDto.setUser(userDto);
        likeDto.setTwit(twitDto);
        return likeDto;
    }

    public static List<LikeDto> toLikeDtos(List<Like> likes,User reqUser) {

        List<LikeDto> likeDtos=new ArrayList<>() ;

        for(Like like:likes) {
            UserDto user=UserDtoMapper.UsertoUserDto(like.getUser());
            TwitDto twit =TwitDtoMapper.TwitToTwitDto(like.getTwit(),reqUser);

            LikeDto likeDto=new LikeDto();
            likeDto.setId(like.getId());
            likeDto.setTwit(twit);
            likeDto.setUser(user);

            likeDtos.add(likeDto);
        }



        return likeDtos;

    }
}
