/*
 * Copyright (C) 2022 Anton Abashkin and contributors as noted in the AUTHORS file
 * SPDX-License-Identifier: MIT
 */

package demo.nosqlinjectionvulnerableapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import demo.nosqlinjectionvulnerableapp.service.IContactsService;
import demo.nosqlinjectionvulnerableapp.util.MongoClientUtil;

@Service
public class InsecureContactsService implements IContactsService {

    private MongoDatabase db = MongoClientUtil.mongoClient.getDatabase("test");
    private MongoCollection<Document> collection = db.getCollection("contacts");

    public InsecureContactsService() {
    }

    /*
     * Uses the `BasicDBObject::put` method to create a query. 
     * Uses the $where operator along with a query string that includes concatenated user input.
     * State: Insecure 
     */
    public ArrayList<Document> basicDBObjectPut(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        query.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    /*
     * Uses the `BasicDBObject::putAll` method to create a query. 
     * Uses the $where operator along with a query string that includes concatenated user input.
     * State: Insecure 
     */
    public ArrayList<Document> basicDBObjectPutAll(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");
        query.putAll(paramMap);

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    /*
     * Uses the `BasicDBObject::append` method to create a query. 
     * Uses the $where operator along with a query string that includes concatenated user input.
     * State: Insecure 
     */
    public ArrayList<Document> basicDBObjectAppend(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        query.append("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    /*
     * Uses the `BasicDBObject(String, Object)` constructor to create a query. 
     * Uses the $where operator along with a query string that includes concatenated user input.
     * State: Insecure 
     */
    public ArrayList<Document> basicDBObjectConstructorKv(String userName, String email) {
        BasicDBObject query = new BasicDBObject("$where",
                "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    /*
     * Uses the `BasicDBObject(Map)` constructor to create a query. 
     * Uses the $where operator along with a query string that includes concatenated user input.
     * State: Insecure 
     */
    public ArrayList<Document> basicDBObjectConstructorMap(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");
        BasicDBObject query = new BasicDBObject(paramMap);

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    /*
     * Uses the `BasicDBObject::parse` method to create a query. 
     * Uses the $where operator along with a query string that includes concatenated user input.
     * State: Insecure 
    */
    public ArrayList<Document> basicDBObjectParse(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");
        String json = new JSONObject(paramMap).toString();
        BasicDBObject query = new BasicDBObject().parse(json);

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    /*
     * Uses the `BasicDBObjectBuilder::add` method to create a query. 
     * Uses the $where operator along with a query string that includes concatenated user input.
     * State: Insecure 
     */
    public ArrayList<Document> basicDBObjectBuilderAdd(String userName, String email) {
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start()
                .add("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"")
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    /*
     * Uses the `BasicDBObjectBuilder::append` method to create a query. 
     * Uses the $where operator along with a query string that includes concatenated user input.
     * State: Insecure 
     */
    public ArrayList<Document> basicDBObjectBuilderAppend(String userName, String email) {
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start()
                .append("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"")
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    /*
     * Uses the `BasicDBObjectBuilder::start(String, Object)` method to create a query. 
     * Uses the $where operator along with a query string that includes concatenated user input.
     * State: Insecure 
     */
    public ArrayList<Document> basicDBObjectBuilderStartKv(String userName, String email) {
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"")
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    /*
     * Uses the `BasicDBObjectBuilder(Map)` constructor to create a query. 
     * Uses the $where operator along with a query string that includes concatenated user input.
     * State: Insecure 
     */
    public ArrayList<Document> basicDBObjectBuilderStartMap(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
                .start(paramMap)
                .get();

        MongoCursor<Document> cursor = collection.find(query).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

}