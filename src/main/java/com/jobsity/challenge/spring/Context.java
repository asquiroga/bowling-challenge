package com.jobsity.challenge.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jobsity.challenge.configuration.AppConfiguration;

public class Context {

	static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

	public static AnnotationConfigApplicationContext getContext() {
		return context;
	}

}
