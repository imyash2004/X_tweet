package com.mazmagic.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mazmagic.model.Like;
import com.mazmagic.model.Twit;
import com.mazmagic.model.User;
import com.mazmagic.model.Varification;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String location;
    private String website;
    private String birthDate;
    private String email;
    private String password;
    private String mobile;
    private String image;
    private String backgroundimage;
    private String bio;


    private boolean req_user;
    private boolean login_with_google;




    private boolean followed;

    private Varification verification;


    public List<UserDto>followers=new ArrayList<>();

    private List<UserDto>followings=new ArrayList<>();

}
