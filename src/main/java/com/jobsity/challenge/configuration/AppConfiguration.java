package com.jobsity.challenge.configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.jobsity.challenge.parser.BowlingParserImpl;
import com.jobsity.challenge.parser.BownlingParser;
import com.jobsity.challenge.util.FramesCreator;
import com.jobsity.challenge.util.FramesCreatorImpl;
import com.jobsity.challenge.util.PointProcessor;
import com.jobsity.challenge.util.PointProcessorImpl;
import com.jobsity.challenge.util.prettyPrint.PrettyPrinter;
import com.jobsity.challenge.util.prettyPrint.PrettyPrinterImpl;

@Configuration
public class AppConfiguration {

	@Bean
	public BownlingParser getParser() {
		return new BowlingParserImpl();
	}

	@Bean
	public FramesCreator getFramesCreator() {
		return new FramesCreatorImpl();
	}
	
	@Bean
	public PrettyPrinter getPrettyPrinter() {
		return new PrettyPrinterImpl();
	}
	
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Bean
	public PointProcessor getPointProcessor() {
		return new PointProcessorImpl();
	}

}
