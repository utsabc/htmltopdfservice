package com.service.resource.internal;

import com.service.resource.pojos.HealthMetric;
import com.service.resource.pojos.InputEntity;
import com.service.resource.pojos.OutputEntity;

public interface ICoreService {

	public HealthMetric heartBeat();
	public OutputEntity process(InputEntity htmlinput) throws Exception;
	
}
