package com.wordpractice.common.utils;

import com.wordpractice.domain.DictionaryDto;
import com.wordpractice.domain.model.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

	public static List<DictionaryDto> convertToDto(List<Dictionary> entityList) {
		List<DictionaryDto> dtoList = new ArrayList<>();
		for (Dictionary dictionary : entityList) {
			dtoList.add(convertToDto(dictionary));
		}

		return dtoList;
	}


	public static DictionaryDto convertToDto(Dictionary entity) {
		DictionaryDto dto = new DictionaryDto();
		dto.setContent(entity.getContent());
		dto.setDictionaryId(entity.getDictionaryId());
		dto.setName(entity.getName());
		dto.setUsername(entity.getUsername());
		return dto;
	}


}
