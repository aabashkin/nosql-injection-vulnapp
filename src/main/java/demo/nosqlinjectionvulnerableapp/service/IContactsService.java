/*
 * Copyright (C) 2022 Anton Abashkin and contributors as noted in the AUTHORS file
 * SPDX-License-Identifier: MIT
 */

package demo.nosqlinjectionvulnerableapp.service;

import java.util.ArrayList;

import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
public interface IContactsService {

    public ArrayList<Document> basicDBObjectPut(String name, String email);

    public ArrayList<Document> basicDBObjectPutAll(String name, String email);

    public ArrayList<Document> basicDBObjectAppend(String name, String email);

    public ArrayList<Document> basicDBObjectConstructorKv(String name, String email);

    public ArrayList<Document> basicDBObjectConstructorMap(String name, String email);

    public ArrayList<Document> basicDBObjectParse(String name, String email);

    public ArrayList<Document> basicDBObjectBuilderAdd(String name, String email);

    public ArrayList<Document> basicDBObjectBuilderAppend(String name, String email);

    public ArrayList<Document> basicDBObjectBuilderStartKv(String name, String email);

    public ArrayList<Document> basicDBObjectBuilderStartMap(String name, String email);
    
}