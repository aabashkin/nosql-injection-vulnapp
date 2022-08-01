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

import demo.nosqlinjectionvulnerableapp.service.impl.SecureContactsService;
import demo.nosqlinjectionvulnerableapp.util.MongoServerUtil;

@SpringBootTest
class SecureContactsServiceIntTest {
    
    @Autowired
    private SecureContactsService secureContactsService;

    @BeforeAll
    static void setupMongoDb() {
        MongoServerUtil.setupMongoDb();
    }

    @AfterAll
    static void destroy() {
        MongoServerUtil.destroy();
    }

    @Test
    void testFindFilterRegularInput() {
        List<Document> results = secureContactsService.bsonFilter("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testFindFilterAccessCheck() {
        List<Document> results = secureContactsService.bsonFilter("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testFindFilterInjection() {
        List<Document> results = secureContactsService.bsonFilter("user1", "\" || \"4\" != \"5");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectPutRegularInput() {
        List<Document> results = secureContactsService.basicDBObjectPut("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectPutAccessCheck() {
        List<Document> results = secureContactsService.basicDBObjectPut("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectPutInjection() {
        List<Document> results = secureContactsService.basicDBObjectPut("user1", "\" || \"4\" != \"5");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectPutAllRegularInput() {
        List<Document> results = secureContactsService.basicDBObjectPutAll("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectPutAllAccessCheck() {
        List<Document> results = secureContactsService.basicDBObjectPutAll("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectPutAllInjection() {
        List<Document> results = secureContactsService.basicDBObjectPutAll("user1", "\" || \"4\" != \"5");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectAppendRegularInput() {
        List<Document> results = secureContactsService.basicDBObjectAppend("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectAppendAccessCheck() {
        List<Document> results = secureContactsService.basicDBObjectAppend("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectAppendInjection() {
        List<Document> results = secureContactsService.basicDBObjectAppend("user1", "\" || \"4\" != \"5");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectConstructorKvRegularInput() {
        List<Document> results = secureContactsService.basicDBObjectConstructorKv("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectConstructorKvAccessCheck() {
        List<Document> results = secureContactsService.basicDBObjectConstructorKv("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectConstructorKvRegularInjection() {
        List<Document> results = secureContactsService.basicDBObjectConstructorKv("user1", "\" || \"4\" != \"5");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectConstructorMapRegularInput() {
        List<Document> results = secureContactsService.basicDBObjectConstructorMap("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectConstructorMapAccessCheck() {
        List<Document> results = secureContactsService.basicDBObjectConstructorMap("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectConstructorMapInjection() {
        List<Document> results = secureContactsService.basicDBObjectConstructorMap("user1", "\" || \"4\" != \"5");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectParseRegularInput() {
        List<Document> results = secureContactsService.basicDBObjectParse("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectParseAcccesCheck() {
        List<Document> results = secureContactsService.basicDBObjectParse("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectParseInjection() {
        List<Document> results = secureContactsService.basicDBObjectParse("user1", "\" || \"4\" != \"5");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderAddRegularInput() {
        List<Document> results = secureContactsService.basicDBObjectBuilderAdd("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectBuilderAddAccessCheck() {
        List<Document> results = secureContactsService.basicDBObjectBuilderAdd("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderAddInjection() {
        List<Document> results = secureContactsService.basicDBObjectBuilderAdd("user1", "\" || \"4\" != \"5");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderAppendRegularInput() {
        List<Document> results = secureContactsService.basicDBObjectBuilderAppend("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }
    
    @Test
    void testBasicDBObjectBuilderAppendAccessCheck() {
        List<Document> results = secureContactsService.basicDBObjectBuilderAppend("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderAppendInjection() {
        List<Document> results = secureContactsService.basicDBObjectBuilderAppend("user1", "\" || \"4\" != \"5");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartKvRegularInput() {
        List<Document> results = secureContactsService.basicDBObjectBuilderStartKv("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartKvAccessCheck() {
        List<Document> results = secureContactsService.basicDBObjectBuilderStartKv("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartKvInjection() {
        List<Document> results = secureContactsService.basicDBObjectBuilderStartKv("user1", "\" || \"4\" != \"5");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartMapRegularInput() {
        List<Document> results = secureContactsService.basicDBObjectBuilderStartMap("user1", "contact1@private.info");
        assertEquals(1, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartMapAccessCheck() {
        List<Document> results = secureContactsService.basicDBObjectBuilderStartMap("user1", "contact2@private.info");
        assertEquals(0, results.size());
    }

    @Test
    void testBasicDBObjectBuilderStartMapInjection() {
        List<Document> results = secureContactsService.basicDBObjectBuilderStartMap("user1", "\" || \"4\" != \"5");
        assertEquals(0, results.size());
    }
}
