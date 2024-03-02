package com.mazmagic.Controller;

import com.mazmagic.config.JwtProvider;
import com.mazmagic.model.User;
import com.mazmagic.model.Varification;
import com.mazmagic.repositories.UserRepo;
import com.mazmagic.response.AuthResponse;
import com.mazmagic.response.UserException;
import com.mazmagic.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Stack;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws UserException {
        String email=user.getEmail();
        String password=user.getPassword();
        String fullName=user.getFullName();
        String dob=user.getBirthDate();

        User isEmailExist=userRepo.findUserByEmail(email);
        if(isEmailExist!=null){
            throw  new UserException("email already exist");
        }
        User createdUser=new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setBirthDate(dob);
        createdUser.setVerification(new Varification());
        User savedUser=userRepo.save(createdUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtProvider.generateToken(authentication);
        AuthResponse res=new AuthResponse(token,true);
        return new ResponseEntity<AuthResponse>(res, HttpStatus.CREATED);


    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse > signin(@RequestBody User user){
        String username=user.getEmail();
        String password=user.getPassword();


        System.out.println(username +" ----- "+password);

        Authentication auth=authenticate(username,password);

        System.out.println(auth);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token=jwtProvider.generateToken(auth);
        AuthResponse res=new AuthResponse(token,true);


        System.out.println(res);
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);

    }

    private Authentication authenticate(String username,String password){
        UserDetails userDetails=customUserDetailService.loadUserByUsername(username);

//        System.out.println("sign in userDetails - "+userDetails);

        System.out.println(username+"    "+userDetails);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid USername");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
//            System.out.println(password);
//            System.out.println(userDetails.getPassword());


//            System.out.println("cvhgjbhxdschvgj,bkjnsd");
            throw new BadCredentialsException("Incoreect password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
}
