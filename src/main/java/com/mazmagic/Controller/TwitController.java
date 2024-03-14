package com.mazmagic.Controller;

import com.mazmagic.dto.TwitDto;
import com.mazmagic.dto.mapper.TwitDtoMapper;
import com.mazmagic.model.Twit;
import com.mazmagic.model.User;
import com.mazmagic.repositories.TwitRepo;
import com.mazmagic.request.TwitReplyRequest;
import com.mazmagic.response.ApiResponse;
import com.mazmagic.response.TwitException;
import com.mazmagic.response.UserException;
import com.mazmagic.service.TwitService;
import com.mazmagic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/twits/")
public class TwitController {
    @Autowired
    private TwitService twitService;
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<TwitDto>createTwit(@RequestBody Twit twit, @RequestHeader("Authorization") String jwt) throws UserException, TwitException {
        System.out.println("content"+twit.getContent());
        User user=userService.findUserProfileByJwt(jwt);
        Twit twit1=twitService.createTwit(twit,user);
        TwitDto twitDto= TwitDtoMapper.TwitToTwitDto(twit1,user);
        return new ResponseEntity<>(twitDto, HttpStatus.CREATED);
    }

    @PostMapping("/reply")
    public ResponseEntity<TwitDto> replyTwit(@RequestBody TwitReplyRequest req,@RequestHeader("Authorization") String jwt)throws UserException, TwitException{
        User user=userService.findUserProfileByJwt(jwt);
        Twit twit=twitService.createdReply(req,user);
        TwitDto twitDto=TwitDtoMapper.TwitToTwitDto(twit,user);
        return new ResponseEntity<>(twitDto,HttpStatus.CREATED);
    }
    @PutMapping("/{twitId}/retwit")
    public ResponseEntity<TwitDto> retwit(@PathVariable Long twitId,
                                          @RequestHeader("Authorization") String jwt )throws UserException, TwitException{
        User user=userService.findUserProfileByJwt(jwt);
        Twit twit=twitService.reTwit(twitId,user);
        TwitDto twitDto=TwitDtoMapper.TwitToTwitDto(twit,user);
        return new ResponseEntity<>(twitDto,HttpStatus.OK);
    }
    @GetMapping("/{twitId}")
    public ResponseEntity<TwitDto> findTwitById(@PathVariable Long twitId,@RequestHeader("Authorization") String jwt) throws UserException,TwitException{
        Twit twit= twitService.findById(twitId);
        User user=userService.findUserProfileByJwt(jwt);
        TwitDto twitDto=TwitDtoMapper.TwitToTwitDto(twit,user);
        return new ResponseEntity<>(twitDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{twitId}")
    public ResponseEntity<ApiResponse>deleteTwitById(@PathVariable Long twitId,@RequestHeader("Authorization") String jwt)throws UserException,TwitException{
        User user=userService.findUserProfileByJwt(jwt);
        twitService.deletTwitById(twitId,user.getId());
        ApiResponse response=new ApiResponse();
        response.setMessage("twit Deted successfully");
        response.setStatus(true);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<TwitDto>> findAllTwits(@RequestHeader("Authorization") String jwt) throws TwitException,UserException{
        User user=userService.findUserProfileByJwt(jwt);
        List<Twit> twits=twitService.findAllTwit();
        List<TwitDto>twitDtos=TwitDtoMapper.toTwitDtos(twits,user);
        return new ResponseEntity<List<TwitDto>>(twitDtos,HttpStatus.OK);


    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TwitDto>> getUsersTwits(@PathVariable Long userId,@RequestHeader("Authorization") String jwt)
            throws UserException{
        User user=userService.findUserProfileByJwt(jwt);
        User req=userService.findUserById(userId);
        List<Twit>twits=twitService.getUserTwit(req);
        List<TwitDto>twitDtos=TwitDtoMapper.toTwitDtos(twits,user);
        return new ResponseEntity<>(twitDtos,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/likes")
    public ResponseEntity<List<TwitDto>>findTwitByLikesContainsUser(@PathVariable Long userId,@RequestHeader("Authorization") String jwt)
            throws UserException{
        User userReq=userService.findUserProfileByJwt(jwt);
        User user=userService.findUserById(userId);
        List<Twit> twits=twitService.findByLikesContainsUser(user);
        List<TwitDto>twitDtos=TwitDtoMapper.toTwitDtos(twits,userReq);
        return new ResponseEntity<>(twitDtos,HttpStatus.OK);

    }












}
