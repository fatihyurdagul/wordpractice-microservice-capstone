package com.wordpractice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.wordpractice.common.utils.AwsDynamoUtils;
import com.wordpractice.domain.model.Dictionary;
import com.wordpractice.domain.request.CreateDictionaryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DictionaryRepository {

    @Autowired
    AwsDynamoUtils dynamoUtils;

    public boolean createDictionary(CreateDictionaryRequest request) {

        try {
            DynamoDBMapper mapper = new DynamoDBMapper(dynamoUtils.getClient());

            Dictionary dictionary = new Dictionary();
            dictionary.setDictionaryId(UUID.randomUUID().toString());
            dictionary.setContent(request.getContent());
            dictionary.setName(request.getName());
            dictionary.setUsername(request.getUsername());

            mapper.save(dictionary);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Dictionary> getDictionaryList(String username) {

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoUtils.getClient());

        Dictionary hashKeyDictionary = new Dictionary();
        hashKeyDictionary.setUsername(username);

        DynamoDBQueryExpression<Dictionary> queryExpression = new DynamoDBQueryExpression<Dictionary>().withHashKeyValues(hashKeyDictionary);


        List<Dictionary> userDictionaryList = mapper.query(Dictionary.class, queryExpression);


        return userDictionaryList;
    }
}
