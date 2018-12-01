package com.example.samantha.habittracker;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.mobileconnectors.dynamodbv2.document.UpdateItemOperationConfig;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import android.content.Context;

import java.util.List;
import java.util.UUID;

public class DynamoDbClient {

    private Context context;
    // Create a new credentials provider
    CognitoCachingCredentialsProvider credentialsProvider;

    // Create a connection to DynamoDB
    AmazonDynamoDBClient dbClient = new AmazonDynamoDBClient(credentialsProvider);
    // Create the table
    Table dbTable;

    public DynamoDbClient(Context context, String tableName)
    {
        this.context = context;
        credentialsProvider = new CognitoCachingCredentialsProvider(context, "us-east-2_4LKOsZR7G", Regions.US_EAST_2);
        dbTable = Table.loadTable(dbClient, tableName);
    }

    public void update(Document memo) {
        Document document = dbTable.updateItem(memo, new UpdateItemOperationConfig().withReturnValues(ReturnValue.ALL_NEW));
    }

    public void create(Document memo) {
        if (!memo.containsKey("userId")) {
            memo.put("userId", credentialsProvider.getCachedIdentityId());
        }
        if (!memo.containsKey("noteId")) {
            memo.put("noteId", UUID.randomUUID().toString());
        }
        if (!memo.containsKey("creationDate")) {
            memo.put("creationDate", System.currentTimeMillis());
        }
        dbTable.putItem(memo);
    }

    public void delete(Document memo) {
        dbTable.deleteItem(
                memo.get("userId").asPrimitive(),
                memo.get("noteId").asPrimitive());
    }

    public Document getById(String id) {
        return dbTable.getItem(new Primitive(credentialsProvider.getCachedIdentityId()), new Primitive(id));
    }

    public List<Document> getAll() {
        return dbTable.query(new Primitive(credentialsProvider.getCachedIdentityId())).getAllResults();
    }
}


