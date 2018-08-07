package com.kotomalism.jsonpath;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.filter.ValueNode.JsonNode;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

public class JsonPathJacksonSample {

	/** Jacksonの設定、JsonPathのtestから拝借 */
	public static final Configuration JACKSON_CONFIGURATION = Configuration
            .builder()
            .mappingProvider(new JacksonMappingProvider())
            .jsonProvider(new JacksonJsonProvider())
            .build();

	/**
	 * 渡したJSON文字列から任意の部分を抜き出して文字列として返すようなやつ。
	 * 
	 * @param json 元Json文字列
	 * @param path JsonPath
	 * @return 抜き出した文字列
	 */
	public static String pickJsonString(String json, String path) {
		JsonPath compiledPath = JsonPath.compile(path);
		JsonNode read = JsonPath.using(JACKSON_CONFIGURATION).parse(json).read(compiledPath);
		return read.isStringNode() ? read.asStringNode().toString() : read.toString();
	}
	
}
