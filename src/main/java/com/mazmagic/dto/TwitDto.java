package com.mazmagic.dto;

import com.mazmagic.model.Twit;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TwitDto {
    private Long id;


    private UserDto user;
    private String content;
    private String image;
    private String video;


    private int totalLikes;

    private int totalReplies;


    private int totalRetweets;

    private List<TwitDto> replyTwits=new ArrayList<>();

    private List<Long> retwitUserId=new ArrayList<>();


    private Twit replyFor;

    private boolean isLiked;
    private boolean isReTwit;
    private LocalDateTime createdAt;





}
