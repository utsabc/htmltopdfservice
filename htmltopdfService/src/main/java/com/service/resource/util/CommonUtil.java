package com.service.resource.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CommonUtil {
	
	private Map<Object,Object> infoMap = new HashMap<>();

	public Map<Object, Object> getInfoMap() {
		return infoMap;
	}

	public void setInfoMap(Map<Object, Object> infoMap) {
		this.infoMap = infoMap;
	}
	
	

}
