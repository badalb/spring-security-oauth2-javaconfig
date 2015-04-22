package com.test.view.rest.presentation;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ViewMapper<T> {

	public ObjectMapper MAPPER = new ObjectMapper();

	public T map(Object obj, Class<T> clazz){
		MAPPER=MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return MAPPER.convertValue(obj, clazz);
	}

}
