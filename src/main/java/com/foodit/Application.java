package com.foodit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.foodit.Application;
import com.foodit.service.RuleService;

@ComponentScan
@EnableAutoConfiguration
public class Application {

	@Autowired
	private RuleService ruleService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
