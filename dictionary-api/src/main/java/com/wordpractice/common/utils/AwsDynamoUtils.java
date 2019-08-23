package com.wordpractice.common.utils;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.wordpractice.config.DynamoDbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AwsDynamoUtils {

    AmazonDynamoDB dynamoDBClient;

    @Autowired
    DynamoDbConfig config;


    public AmazonDynamoDB getClient() {
        if (dynamoDBClient != null) return dynamoDBClient;

        dynamoDBClient = AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(config.getKey(), config.getSecretKey())))
                .withRegion(config.getRegion())
                .build();

        return dynamoDBClient;
    }

}
