/*
 * Copyright (C) 2022 Anton Abashkin and contributors as noted in the AUTHORS file
 * SPDX-License-Identifier: MIT
 */

package demo.nosqlinjectionvulnerableapp.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoClientUtil {

    public static final String uri = "mongodb://localhost:27017";
    public static final MongoClient mongoClient = MongoClients.create(uri);

}
