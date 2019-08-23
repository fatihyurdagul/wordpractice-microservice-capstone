package com.wordpractice.service;

import com.wordpractice.common.utils.ObjectMapper;
import com.wordpractice.domain.DictionaryDto;
import com.wordpractice.domain.model.Dictionary;
import com.wordpractice.domain.request.CreateDictionaryRequest;
import com.wordpractice.domain.response.GenericResponse;
import com.wordpractice.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class DictionaryService {

    @Autowired
    DictionaryRepository dictionaryRepository;

    public GenericResponse<List<DictionaryDto>> getDictionaryList(String username) {
        List<Dictionary> dictionaryList = dictionaryRepository.getDictionaryList(username);

        List<DictionaryDto> dtoList = ObjectMapper.convertToDto(dictionaryList);

        GenericResponse<List<DictionaryDto>> response = new GenericResponse<>();

        response.setStatus(HttpStatus.OK.value());
        response.setData(dtoList);
        response.setMessage("");

        return response;
    }


    public GenericResponse<Boolean> createDictionary(CreateDictionaryRequest request) {
        boolean result = dictionaryRepository.createDictionary(request);

        GenericResponse<Boolean> response = new GenericResponse<>();

        response.setData(result);
        response.setStatus(result ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value());

        return response;
    }
}
