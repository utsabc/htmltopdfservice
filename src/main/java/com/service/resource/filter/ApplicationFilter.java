package com.service.resource.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.resource.util.CommonUtil;


@Component
public class ApplicationFilter implements Filter{

	private final Logger LOG = LoggerFactory.getLogger(ApplicationFilter.class);

	@Autowired
	CommonUtil commonUtil;

	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		LOG.info( "Starting a transaction for req : {}", req.getRequestURI());
		getHeaderInfo(req);
		commonUtil.getInfoMap();
		LOG.info("Committing a transaction for req : {}", req.getRequestURI());
		chain.doFilter(request, response);
	}

	private void getHeaderInfo(HttpServletRequest request) {
		Map<String, Object> headersMap = Collections.list(request.getHeaderNames())    
				.stream()
				.collect(Collectors.toMap(
						Function.identity(), 
						h -> Collections.list(request.getHeaders(h))
						));
		LOG.info("Headers received:");
		headersMap.forEach((k, v) ->LOG.info((k + ":" + v)));
		commonUtil.setInfoMap(new HashMap<Object, Object>(headersMap));
	}
	
	// this method will be called by container while deployment
	@Override
    public void init(FilterConfig config) throws ServletException {
 
        System.out.println("init() method has been get invoked");
        System.out.println("Filter name is "+config.getFilterName());
        System.out.println("ServletContext name is"+config.getServletContext().getServletContextName());
        System.out.println("init() method is ended");
    }
 
	@Override
    public void destroy() {
        //to do some stuff like clearing the resources
    }

}
