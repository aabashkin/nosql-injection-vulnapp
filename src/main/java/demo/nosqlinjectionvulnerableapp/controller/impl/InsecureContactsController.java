/*
 * Copyright (C) 2022 Anton Abashkin and contributors as noted in the AUTHORS file
 * SPDX-License-Identifier: MIT
 */

package demo.nosqlinjectionvulnerableapp.controller.impl;

import java.util.ArrayList;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.nosqlinjectionvulnerableapp.service.impl.InsecureContactsService;

@RestController
@RequestMapping("insecure")
public class InsecureContactsController {

	@Autowired
	private InsecureContactsService insecureContactsService;

	@GetMapping("/basicdbobject-put/contacts/search") public ArrayList<Document> basicDBObjectPut(@RequestParam String email, Authentication authentication) {
		ArrayList<Document> results = insecureContactsService.basicDBObjectPut(authentication.getName(), email);
		return results;
	}

	@GetMapping("/basicdbobject-putall/contacts/search") public ArrayList<Document> basicDBObjectPutAll(@RequestParam String email, Authentication authentication) {
		ArrayList<Document> results = insecureContactsService.basicDBObjectPutAll(authentication.getName(), email);
		return results;
	}

	@GetMapping("/basicdbobject-append/contacts/search") public ArrayList<Document> basicDBObjectAppend(@RequestParam String email, Authentication authentication) {
		ArrayList<Document> results = insecureContactsService.basicDBObjectAppend(authentication.getName(), email);
		return results;
	}

	@GetMapping("/basicdbobject-constructor-kv/contacts/search") public ArrayList<Document> basicDBObjectConstructorKv(@RequestParam String email, Authentication authentication) {
		ArrayList<Document> results = insecureContactsService.basicDBObjectConstructorKv(authentication.getName(), email);
		return results;
	}

	@GetMapping("/basicdbobject-constructor-map/contacts/search") public ArrayList<Document> basicDBObjectConstructorMap(@RequestParam String email, Authentication authentication) {
		ArrayList<Document> results = insecureContactsService.basicDBObjectConstructorMap(authentication.getName(), email);
		return results;
	}

	@GetMapping("/basicdbobject-parse/contacts/search") public ArrayList<Document> basicDBObjectParse(@RequestParam String email, Authentication authentication) {
		ArrayList<Document> results = insecureContactsService.basicDBObjectParse(authentication.getName(), email);
		return results;
	}

	@GetMapping("/basicdbobjectbuilder-add/contacts/search") public ArrayList<Document> basicDBObjectBuilderAdd(@RequestParam String email, Authentication authentication) {
		ArrayList<Document> results = insecureContactsService.basicDBObjectBuilderAdd(authentication.getName(), email);
		return results;
	}

	@GetMapping("/basicdbobjectbuilder-append/contacts/search") public ArrayList<Document> basicDBObjectBuilderAppend(@RequestParam String email, Authentication authentication) {
		ArrayList<Document> results = insecureContactsService.basicDBObjectBuilderAppend(authentication.getName(), email);
		return results;
	}

	@GetMapping("/basicdbobjectbuilder-start-kv/contacts/search") public ArrayList<Document> basicDBObjectBuilderStartKv(@RequestParam String email, Authentication authentication) {
		ArrayList<Document> results = insecureContactsService.basicDBObjectBuilderStartKv(authentication.getName(), email);
		return results;
	}

	@GetMapping("/basicdbobjectbuilder-start-map/contacts/search") public ArrayList<Document> basicDBObjectBuilderStartMap(@RequestParam String email, Authentication authentication) {
		ArrayList<Document> results = insecureContactsService.basicDBObjectBuilderStartMap(authentication.getName(), email);
		return results;
	}
	
}
