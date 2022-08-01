/*
 * Copyright (C) 2022 Anton Abashkin and contributors as noted in the AUTHORS file
 * SPDX-License-Identifier: MIT
 */

package demo.nosqlinjectionvulnerableapp.controller;

import java.util.ArrayList;

import org.bson.Document;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface IContactsController {

    public ArrayList<Document> basicDBObjectPut(@RequestParam String email, Authentication authentication);

	public ArrayList<Document> basicDBObjectPutAll(@RequestParam String email, Authentication authentication);

	public ArrayList<Document> basicDBObjectAppend(@RequestParam String email, Authentication authentication);

	public ArrayList<Document> basicDBObjectConstructorKv(@RequestParam String email, Authentication authentication);

	public ArrayList<Document> basicDBObjectConstructorMap(@RequestParam String email, Authentication authentication);

	public ArrayList<Document> basicDBObjectParse(@RequestParam String email, Authentication authentication);

	public ArrayList<Document> basicDBObjectBuilderAdd(@RequestParam String email, Authentication authentication);

	public ArrayList<Document> basicDBObjectBuilderAppend(@RequestParam String email, Authentication authentication);

	public ArrayList<Document> basicDBObjectBuilderStartKv(@RequestParam String email, Authentication authentication);

	public ArrayList<Document> basicDBObjectBuilderStartMap(@RequestParam String email, Authentication authentication);

}