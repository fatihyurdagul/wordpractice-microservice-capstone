package com.wordpractice.controller;

import com.wordpractice.domain.request.UserLoginRequest;
import com.wordpractice.domain.request.UserRegisterRequest;
import com.wordpractice.domain.response.GenericResponse;
import com.wordpractice.domain.response.UserResponse;
import com.wordpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fatihyurdagul on 18.08.2019.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/list")
    public List<String> getUserList() {
        return Arrays.asList("Selam", "Naber");
    }

    @PostMapping(path = "/register")
    public void registerUser(@RequestBody UserRegisterRequest registerRequest) {
        userService.registerUser(registerRequest);
    }

    @PostMapping(path = "/login")
    public GenericResponse<UserResponse> loginUser(@RequestBody UserLoginRequest loginRequest) {

        GenericResponse genericResponse = userService.loginUser(loginRequest);
//        if (genericResponse.getData() != null && genericResponse.getStatus() == 200) {
//            UsernamePasswordAuthenticationToken authToken =
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
//            authenticationManager.authenticate(authToken);
//        }

        return genericResponse;

    }


}
