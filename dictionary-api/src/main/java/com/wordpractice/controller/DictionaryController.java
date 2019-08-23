package com.wordpractice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordpractice.domain.DictionaryDto;
import com.wordpractice.domain.model.Dictionary;
import com.wordpractice.domain.request.CreateDictionaryRequest;
import com.wordpractice.domain.response.GenericResponse;
import com.wordpractice.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@RequestMapping(path = "/dictionary")
@EnableWebMvc
public class DictionaryController {

    @Autowired
    DictionaryService dictionaryService;


    @GetMapping(value = "/{username:.+}", produces = MediaType.ALL_VALUE, headers = "Accept=*/*")
    public String getDictionaryList(@PathVariable("username") String username) throws JsonProcessingException {
        GenericResponse<List<DictionaryDto>> dictionaryList = dictionaryService.getDictionaryList(username);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dictionaryList);
    }

    @PostMapping(path = "/add")
    public GenericResponse<Boolean> createDictionary(@RequestBody CreateDictionaryRequest request) {
        return dictionaryService.createDictionary(request);
    }

}
