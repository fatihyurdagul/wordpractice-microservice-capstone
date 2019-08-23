package com.wordpractice.domain.model;


import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "dictionary")
public class Dictionary {

	private String username;
	private String dictionaryId;
	private String name;
	private String content;


	@DynamoDBHashKey
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@DynamoDBRangeKey
	public String getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}


	@DynamoDBIndexRangeKey(attributeName="name",
		localSecondaryIndexName="username-name-index")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBAttribute(attributeName = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
