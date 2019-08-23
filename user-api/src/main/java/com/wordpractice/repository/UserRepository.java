package com.wordpractice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import com.wordpractice.common.utils.AwsDynamoUtils;
import com.wordpractice.common.utils.JwtTokenUtil;
import com.wordpractice.domain.model.User;
import com.wordpractice.domain.request.UserLoginRequest;
import com.wordpractice.domain.request.UserRegisterRequest;
import com.wordpractice.domain.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    AwsDynamoUtils dynamoUtils;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public void registerUser(UserRegisterRequest request) {

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoUtils.getClient());

        User userModel = new User();

        userModel.setFullName(request.getFullName());
        userModel.setPassword(request.getPassword());
        userModel.setUsername(request.getUsername());

        mapper.save(userModel);
    }

    public UserResponse loginUser(UserLoginRequest request) {

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoUtils.getClient());

        User user = mapper.load(User.class, request.getUsername());

        if (user != null) {
            if (user.getPassword().equals(request.getPassword())) {
                UserResponse userResponse = new UserResponse();
                userResponse.setUsername(user.getUsername());

                final String token = jwtTokenUtil.generateToken(user.getUsername());
                userResponse.setJwtToken(token);
                return userResponse;
            }
        }
        return null;
    }


    public UserResponse checkUsername(String username) {

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoUtils.getClient());

        User user = mapper.load(User.class, username);

        if (user != null) {

            UserResponse userResponse = new UserResponse();
            userResponse.setUsername(user.getUsername());

            return userResponse;

        }
        return null;
    }
}
