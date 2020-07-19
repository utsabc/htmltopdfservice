package com.service.resource.pojos;

public class HealthMetric {

	private long uptime;
	private String status = "Alive";
	
	public HealthMetric(long uptime){
		this.uptime = uptime;
	}

	public long getUptime() {
		return uptime;
	}

	public String getStatus() {
		return status;
	}
	
	
	
}
