/*
 * Copyright (C) 2022 Anton Abashkin and contributors as noted in the AUTHORS file
 * SPDX-License-Identifier: MIT
 */

package demo.nosqlinjectionvulnerableapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import demo.nosqlinjectionvulnerableapp.service.impl.InsecureContactsService;
import demo.nosqlinjectionvulnerableapp.util.MongoServerUtil;

@SpringBootTest
class InsecureContactsServiceIntTest {
    
    @Autowired
    private InsecureContactsService insecureContactsService;

    @BeforeAll
    static void setupMongoDb() {
        MongoServerUtil.setupMongoDb();
    }

    @AfterAll
    static void destroy() {
        MongoServerUtil.destroy();
    }

    @Test
    void testBasicDBObjectPutRegularInput() {
        List<Document> results = insecureContactsService.basicDBObjectPut("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectPutAccessCheck() {
        List<Document> results = insecureContactsService.basicDBObjectPut("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectPutInjection() {
        List<Document> results = insecureContactsService.basicDBObjectPut("user1", "\" || \"4\" != \"5");
        assertEquals(3, results.size());
    }

    @Test
    void testBasicDBObjectPutAllRegularInput() {
        List<Document> results = insecureContactsService.basicDBObjectPutAll("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectPutAllAccessCheck() {
        List<Document> results = insecureContactsService.basicDBObjectPutAll("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectPutAllInjection() {
        List<Document> results = insecureContactsService.basicDBObjectPutAll("user1", "\" || \"4\" != \"5");
        assertEquals(3, results.size());
    }

    @Test
    void testBasicDBObjectAppendRegularInput() {
        List<Document> results = insecureContactsService.basicDBObjectAppend("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectAppendAccessCheck() {
        List<Document> results = insecureContactsService.basicDBObjectAppend("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectAppendInjection() {
        List<Document> results = insecureContactsService.basicDBObjectAppend("user1", "\" || \"4\" != \"5");
        assertEquals(3, results.size());
    }

    @Test
    void testBasicDBObjectConstructorKvRegularInput() {
        List<Document> results = insecureContactsService.basicDBObjectConstructorKv("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectConstructorKvAccessCheck() {
        List<Document> results = insecureContactsService.basicDBObjectConstructorKv("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectConstructorKvRegularInjection() {
        List<Document> results = insecureContactsService.basicDBObjectConstructorKv("user1", "\" || \"4\" != \"5");
        assertEquals(3, results.size());
    }

    @Test
    void testBasicDBObjectConstructorMapRegularInput() {
        List<Document> results = insecureContactsService.basicDBObjectConstructorMap("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectConstructorMapAccessCheck() {
        List<Document> results = insecureContactsService.basicDBObjectConstructorMap("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectConstructorMapInjection() {
        List<Document> results = insecureContactsService.basicDBObjectConstructorMap("user1", "\" || \"4\" != \"5");
        assertEquals(3, results.size());
    }

    @Test
    void testBasicDBObjectParseRegularInput() {
        List<Document> results = insecureContactsService.basicDBObjectParse("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectParseAcccesCheck() {
        List<Document> results = insecureContactsService.basicDBObjectParse("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectParseInjection() {
        List<Document> results = insecureContactsService.basicDBObjectParse("user1", "\" || \"4\" != \"5");
        assertEquals(3, results.size());
    }

    @Test
    void testBasicDBObjectBuilderAddRegularInput() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderAdd("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectBuilderAddAccessCheck() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderAdd("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderAddInjection() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderAdd("user1", "\" || \"4\" != \"5");
        assertEquals(3, results.size());
    }

    @Test
    void testBasicDBObjectBuilderAppendRegularInput() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderAppend("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }
    
    @Test
    void testBasicDBObjectBuilderAppendAccessCheck() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderAppend("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderAppendInjection() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderAppend("user1", "\" || \"4\" != \"5");
        assertEquals(3, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartKvRegularInput() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderStartKv("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartKvAccessCheck() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderStartKv("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartKvInjection() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderStartKv("user1", "\" || \"4\" != \"5");
        assertEquals(3, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartMapRegularInput() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderStartMap("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartMapAccessCheck() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderStartMap("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartMapInjection() {
        List<Document> results = insecureContactsService.basicDBObjectBuilderStartMap("user1", "\" || \"4\" != \"5");
        assertEquals(3, results.size());
    }
}
