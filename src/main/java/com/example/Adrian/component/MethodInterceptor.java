package com.example.Adrian.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component("methodInterceptor")
public class MethodInterceptor {
	
	private static final Log LOG = LogFactory.getLog(MethodInterceptor.class);

	public static void info(String controlador, String method, String data) {
		LOG.info("METHOD: " + method + " FROM CONTROLLER: " + controlador + " ---- DATA HANDLED: " + data);
	}
}
