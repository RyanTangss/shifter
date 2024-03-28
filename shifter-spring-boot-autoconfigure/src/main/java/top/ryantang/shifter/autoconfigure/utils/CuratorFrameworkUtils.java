package top.ryantang.shifter.autoconfigure.utils;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import top.ryantang.shifter.autoconfigure.properties.ZookeeperProperties;

import java.util.concurrent.TimeUnit;

/**
 * CuratorFrameworkUtils.class
 *
 * @author RyanTang
 * @date 2024/3/20 13:17
 * Create by Intellij idea!
 */
@Slf4j
public abstract class CuratorFrameworkUtils {

    public static ServiceDiscovery<Object> serviceDiscovery(CuratorFramework curatorFramework) {
//        curatorFramework.start();
        JsonInstanceSerializer<Object> serializer = new JsonInstanceSerializer<>(Object.class);
        return ServiceDiscoveryBuilder.builder(Object.class)
                .client(curatorFramework)
                .basePath("/services")
                .serializer(serializer)
                .build();
    }

    /**
     * 连接zk获取CuratorFramework
     * @param zookeeperProperties zk配置信息
     * @return CuratorFramework实例
     */
    public static CuratorFramework curatorFrameworkBuild(ZookeeperProperties zookeeperProperties) throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zookeeperProperties.getAddr())
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(zookeeperProperties.getSessionTimeout())
                .connectionTimeoutMs(zookeeperProperties.getConnectionTimeout())
                .build();
        client.start();
        client.blockUntilConnected(zookeeperProperties.getConnectionTimeout(), TimeUnit.MILLISECONDS);
        log.info("CuratorFramework client:{}", JSONObject.toJSONString(client));
        return client;
    }
}
