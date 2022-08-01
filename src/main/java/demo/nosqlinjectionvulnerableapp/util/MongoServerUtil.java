/*
 * Copyright (C) 2022 Anton Abashkin and contributors as noted in the AUTHORS file
 * SPDX-License-Identifier: MIT
 */

package demo.nosqlinjectionvulnerableapp.util;

import java.util.Arrays;
import java.util.List;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class MongoServerUtil {

    private static MongodExecutable mongodExecutable;
    
    public static void setupMongoDb() {
		try {
			String ip = "localhost";
			int port = 27017;
	
			ImmutableMongodConfig mongodConfig = MongodConfig
				.builder()
				.version(Version.Main.PRODUCTION)
				.net(new Net(ip, port, Network.localhostIsIPv6()))
				.build();
	
			MongodStarter starter = MongodStarter.getDefaultInstance();
			mongodExecutable = starter.prepare(mongodConfig);
			mongodExecutable.start();
		} catch(Exception e) {
			System.out.println(e);
		}

		try {
			MongoDatabase db = MongoClientUtil.mongoClient.getDatabase("test");
			db.getCollection("contacts").drop();
			db.createCollection("contacts");
			MongoCollection<Document> collection = db.getCollection("contacts");

			List<Document> users = Arrays.asList(
				new Document().append("email", "contact1@private.info")
					.append("address", "123 Fake St")
					.append("phone", "111-111-1111")
					.append("sharedWith", "user1"),
				new Document().append("email", "contact2@private.info")
					.append("address", "456 Fake St")
					.append("phone", "222-222-2222")
					.append("sharedWith", "user2"),
				new Document().append("email", "contact3@private.info")
					.append("address", "789 Fake St")
					.append("phone", "333-333-3333")
					.append("sharedWith", "user3")
			);
			try {
				collection.insertMany(users);
			} catch (MongoException me) {
				System.out.println(me);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}

    public static void destroy() {
        mongodExecutable.stop();
    }
}
