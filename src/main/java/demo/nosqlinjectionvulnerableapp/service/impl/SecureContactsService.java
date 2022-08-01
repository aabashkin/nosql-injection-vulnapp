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
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import demo.nosqlinjectionvulnerableapp.service.IContactsService;
import demo.nosqlinjectionvulnerableapp.util.MongoClientUtil;

@Service
public class SecureContactsService implements IContactsService {

    private MongoDatabase db = MongoClientUtil.mongoClient.getDatabase("test");
    private MongoCollection<Document> collection = db.getCollection("contacts");
    
    public SecureContactsService() { }

    /*
     * Using `Filters` should be the first approach considered for constructing queries.
     * Recommended by official docs https://www.mongodb.com/docs/drivers/java/sync/current/fundamentals/crud/read-operations/retrieve/
     * Status: Secure
    */
    public ArrayList<Document> bsonFilter(String userName, String email) {
        Bson filter = Filters.and(Filters.eq("sharedWith", userName), Filters.eq("email", email));

        MongoCursor<Document> cursor = collection.find(filter).iterator();
        ArrayList<Document> results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            results.add(doc);
        }

        return results;
    }

    /*
     * Uses the `BasicDBObject::put` method to create a query. 
     * Avoids using the $where operator along with a query string that includes concatenated user input.
     * Instead incrementally adds search criteria (key/value pairs) to the query.
     * State: Secure 
     */
    public ArrayList<Document> basicDBObjectPut(String userName, String email) {
		BasicDBObject query = new BasicDBObject();
		query.put("sharedWith", userName);
        query.put("email", email);

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
     * Avoids using the $where operator along with a query string that includes concatenated user input.
     * Instead incrementally adds search criteria (key/value pairs) to the query.
     * State: Secure 
     */
    public ArrayList<Document> basicDBObjectPutAll(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("sharedWith", userName);
        paramMap.put("email", email);
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
     * Avoids using the $where operator along with a query string that includes concatenated user input.
     * Instead incrementally adds search criteria (key/value pairs) to the query.
     * State: Secure 
     */
    public ArrayList<Document> basicDBObjectAppend(String userName, String email) {
        BasicDBObject query = new BasicDBObject();
        query.append("sharedWith", userName);
        query.append("email", email);

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
     * Avoids using the $where operator along with a query string that includes concatenated user input.
     * Instead incrementally adds search criteria (key/value pairs) to the query.
     * State: Secure 
     */
    public ArrayList<Document> basicDBObjectConstructorKv(String userName, String email) {
        BasicDBObject query = new BasicDBObject("sharedWith", userName);
        query.append("email", email);

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
     * Avoids using the $where operator along with a query string that includes concatenated user input.
     * Instead incrementally adds search criteria (key/value pairs) to the query.
     * State: Secure 
     */
    public ArrayList<Document> basicDBObjectConstructorMap(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("sharedWith", userName);
        paramMap.put("email", email);
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
     * Avoids using the $where operator along with a query string that includes concatenated user input.
     * Instead incrementally adds search criteria (key/value pairs) to the query.
     * State: Secure 
     */
    public ArrayList<Document> basicDBObjectParse(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("sharedWith", userName);
        paramMap.put("email", email);
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
     * Avoids using the $where operator along with a query string that includes concatenated user input.
     * Instead incrementally adds search criteria (key/value pairs) to the query.
     * State: Secure 
     */
    public ArrayList<Document> basicDBObjectBuilderAdd(String userName, String email) {
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
            .start()
            .add("sharedWith", userName)
            .add("email", email)
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
     * Avoids using the $where operator along with a query string that includes concatenated user input.
     * Instead incrementally adds search criteria (key/value pairs) to the query.
     * State: Secure 
     */
    public ArrayList<Document> basicDBObjectBuilderAppend(String userName, String email) {
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
            .start()
            .append("sharedWith", userName)
            .append("email", email)
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
     * Avoids using the $where operator along with a query string that includes concatenated user input.
     * Instead incrementally adds search criteria (key/value pairs) to the query.
     * State: Secure 
     */
    public ArrayList<Document> basicDBObjectBuilderStartKv(String userName, String email) {
        BasicDBObject query = (BasicDBObject) BasicDBObjectBuilder
            .start("sharedWith", userName)
            .append("email", email)
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
     * Avoids using the $where operator along with a query string that includes concatenated user input.
     * Instead incrementally adds search criteria (key/value pairs) to the query.
     * State: Secure 
     */
    public ArrayList<Document> basicDBObjectBuilderStartMap(String userName, String email) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("sharedWith", userName);
        paramMap.put("email", email);
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