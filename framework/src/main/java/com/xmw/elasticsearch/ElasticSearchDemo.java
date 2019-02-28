package com.xmw.elasticsearch;

import java.util.Objects;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.alibaba.fastjson.JSONObject;
import com.xmw.elasticsearch.holder.ElasticSearchClientHolder;

/**
 * es demo
 */
public class ElasticSearchDemo {

    public static void main(String[] args) throws Exception {
        TransportClient client = ElasticSearchClientHolder.getXpackTransportClient();

        // create
//        System.out.println(create(client));
        JSONObject jsonObj = new JSONObject();
        for (int i = 0; i < 10000; i++) {
            jsonObj.put("id", String.valueOf(i));
            jsonObj.put("name", "star" + i);
            jsonObj.put("dream", "dream" + i);
            jsonObj.put("password", "pwd" + i);
            System.out.println(create(client, jsonObj));
        }

        // get
//        System.out.println(get(client));

        // update
//        System.out.println(update(client));

        // delete
//        System.out.println(delete(client));

        client.close();
    }

    public static String create(TransportClient client, JSONObject jsonObj) {
        Objects.requireNonNull(client);
        return client.prepareIndex("test", "test", jsonObj.getString("id"))
                .setSource(jsonObj.toString(), XContentType.JSON).execute().actionGet().getId();
    }

    public static String get(TransportClient client) {
        return client.prepareGet("test", "test", "2").execute().actionGet().getSourceAsString();
    }

    public static String update(TransportClient client) {
        Objects.requireNonNull(client);
        JSONObject obj = new JSONObject();
        obj.put("test2", "test2222");
        return client.prepareIndex("test", "test", "2")
                .setSource(obj.toString(), XContentType.JSON).execute().actionGet().getId();
    }

    public static String delete(TransportClient client, String id) {
        return client.prepareDelete("test", "test", id).execute().actionGet().getId();
    }
}
