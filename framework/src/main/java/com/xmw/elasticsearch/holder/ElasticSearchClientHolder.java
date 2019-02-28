package com.xmw.elasticsearch.holder;

import java.net.InetAddress;
import java.util.Objects;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

public class ElasticSearchClientHolder {

    private ElasticSearchClientHolder() {
    }

    private static Settings.Builder commonSettings() {
        return Settings.builder()
                // 设置集群名称
                .put("cluster.name", "elasticsearch_xmw")
                // 增加自动嗅探配置, 自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .put("client.transport.sniff", true)
                // 设置true ，忽略连接节点集群名验证
                .put("client.transport.ignore_cluster_name", Boolean.FALSE)
                // ping一个节点的响应时间 默认5秒
                .put("client.transport.ping_timeout", "5s")
                // sample/ping 节点的时间间隔，默认是5s
                .put("client.transport.nodes_sampler_interval", "5s");
    }

    public static TransportClient getTransportClient() throws Exception {
        return new PreBuiltTransportClient(commonSettings().build())
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
    }

    /**
     * 如果es集群安装了x-pack插件则以此种方式连接集群
     * 1. java客户端的方式是以tcp协议在9300端口上进行通信
     * 2. http客户端的方式是以http协议在9200端口上进行通信
     */
    public static TransportClient getXpackTransportClient() throws Exception {
        Settings.Builder builder = commonSettings().put("xpack.security.user", "elastic:elastic");
        return new PreBuiltXPackTransportClient(builder.build())
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
    }

    public static BulkProcessor bulkProcessor() throws Exception {
        return BulkProcessor.builder(getXpackTransportClient(), new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long l, BulkRequest bulkRequest) {
                // log
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
                if (bulkResponse.hasFailures()) {
                    // log
                }
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {
                // log
            }

        }).setBulkActions(1000)
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
                .setFlushInterval(TimeValue.timeValueSeconds(1))
                /*
                  XXX 设置为0时表示将同步的执行请求，否则将异步的执行请求
                  异步请求时，通过flush()并不能保证所有请求已经被写入到ES服务器中
                  对于数据量不大的业务，可以牺牲一定的性能保证每次flush都能被写入
                 */
                .setConcurrentRequests(0)
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();
    }

    public static void closeClient(TransportClient client) {
        if (Objects.nonNull(client)) {
            client.close();
        }
    }
}
