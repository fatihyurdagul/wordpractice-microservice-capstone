package com.wordpractice.service;

import com.wordpractice.common.utils.JwtTokenUtil;
import com.wordpractice.domain.request.UserLoginRequest;
import com.wordpractice.domain.request.UserRegisterRequest;
import com.wordpractice.domain.response.GenericResponse;
import com.wordpractice.domain.response.UserResponse;
import com.wordpractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public void registerUser(UserRegisterRequest request) {
        userRepository.registerUser(request);
    }

    public GenericResponse loginUser(UserLoginRequest request) {
        UserResponse userResponse = userRepository.loginUser(request);

        GenericResponse<UserResponse> response = new GenericResponse<>();
        if (userResponse != null) {
            response.setData(userResponse);
            response.setStatus(HttpStatus.OK.value());
            return response;
        }

        response.setStatus(HttpStatus.NO_CONTENT.value());
        return response;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("javainuse".equals(username)) {

            UserResponse userResponse = userRepository.checkUsername(username);
            final String token = jwtTokenUtil.generateToken(userResponse.getUsername());
            userResponse.setJwtToken(token);

            return userResponse;

        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
