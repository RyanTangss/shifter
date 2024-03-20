package top.ryantang.shifter.autoconfigure.utils;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;

import java.net.URL;

/**
 * CuratorFrameworkUtils.class
 *
 * @author RyanTang
 * @date 2024/3/20 13:17
 * Create by Intellij idea!
 */
public abstract class CuratorFrameworkUtils {

    public static ServiceDiscovery<Object>serviceDiscoveryBuild(CuratorFramework curatorFramework, String basePath){
        return ServiceDiscoveryBuilder.builder(Object.class)
                .basePath(basePath)
                .build();
    }

    /**
     * 连接zk获取CuratorFramework
     * @param connectionUrl 连接zk地址
     * @return CuratorFramework实例
     */
    public static CuratorFramework curatorFrameworkBuild(String connectionUrl){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connectionUrl)
                .retryPolicy(retryPolicy)
//                .sessionTimeoutMs()
//                .connectionTimeoutMs()
                .build();
        curatorFramework.start();
        return curatorFramework;
    }
}
