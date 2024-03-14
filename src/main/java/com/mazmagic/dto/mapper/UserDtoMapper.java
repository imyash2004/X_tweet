package com.mazmagic.dto.mapper;

import com.mazmagic.dto.UserDto;
import com.mazmagic.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDtoMapper {
    public static UserDto UsertoUserDto(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setImage(user.getImage());
        userDto.setBackgroundimage(user.getBackgroundimage());
        userDto.setBio(user.getBio());
        userDto.setEmail(user.getEmail());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setLocation(user.getLocation());
        userDto.setFollowers(toUserDtos(user.getFollowers()));
        userDto.setFollowings(toUserDtos(user.getFollowings()));

        return userDto;
    }
    public static List<UserDto> toUserDtos(List<User > user){
        List<UserDto> userDtos=new ArrayList<>();
        for (User u:user){
            UserDto userDto=new UserDto();
            userDto.setId(u.getId());
            userDto.setEmail(u.getEmail());
            userDto.setFullName(u.getFullName());
            userDto.setImage(u.getImage());
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
