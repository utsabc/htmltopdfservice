package com.service.resource.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.resource.internal.ICoreService;
import com.service.resource.pojos.HealthMetric;
import com.service.resource.pojos.InputEntity;
import com.service.resource.pojos.OutputEntity;


@Controller
@RequestMapping("/service")
public class ApplicationRestController {


	@Autowired
	ICoreService coreService;

	@RequestMapping(value = "/heartBeat", method = RequestMethod.GET)
	public ResponseEntity<HealthMetric> index() {
		return ResponseEntity.ok(coreService.heartBeat());
	}
	

	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	public ResponseEntity<OutputEntity> generate(@RequestBody InputEntity input) throws Exception {
		return ResponseEntity.ok(coreService.process(input));
	}
}
