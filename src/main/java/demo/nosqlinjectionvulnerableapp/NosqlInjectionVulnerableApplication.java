/*
 * Copyright (C) 2022 Anton Abashkin and contributors as noted in the AUTHORS file
 * SPDX-License-Identifier: MIT
 */

package demo.nosqlinjectionvulnerableapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import demo.nosqlinjectionvulnerableapp.util.MongoServerUtil;

@SpringBootApplication
public class NosqlInjectionVulnerableApplication {
	public static void main(String[] args) {
		MongoServerUtil.setupMongoDb();
		SpringApplication.run(NosqlInjectionVulnerableApplication.class, args);
	}
}
