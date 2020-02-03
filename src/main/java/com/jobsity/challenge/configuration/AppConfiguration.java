package com.jobsity.challenge.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jobsity.challenge.parser.BowlingParserImpl;
import com.jobsity.challenge.parser.BownlingParser;
import com.jobsity.challenge.util.FramesCreator;
import com.jobsity.challenge.util.FramesCreatorImpl;
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

}
