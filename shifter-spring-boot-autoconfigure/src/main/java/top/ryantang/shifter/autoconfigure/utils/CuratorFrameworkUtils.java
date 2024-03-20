package top.ryantang.shifter.autoconfigure.utils;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import top.ryantang.shifter.autoconfigure.entity.ServiceInstanceEntity;
import top.ryantang.shifter.autoconfigure.properties.ZookeeperProperties;

/**
 * CuratorFrameworkUtils.class
 *
 * @author RyanTang
 * @date 2024/3/20 13:17
 * Create by Intellij idea!
 */
public abstract class CuratorFrameworkUtils {

    public static ServiceDiscovery<ServiceInstanceEntity>serviceDiscovery(CuratorFramework curatorFramework){
        curatorFramework.start();
        return ServiceDiscoveryBuilder.builder(ServiceInstanceEntity.class)
                .basePath("/services")
                .build();
    }

    /**
     * 连接zk获取CuratorFramework
     * @param zookeeperProperties zk配置信息
     * @return CuratorFramework实例
     */
    public static CuratorFramework curatorFrameworkBuild(ZookeeperProperties zookeeperProperties){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zookeeperProperties.getAddr())
                .retryPolicy(retryPolicy)
//                .sessionTimeoutMs()
                .connectionTimeoutMs(zookeeperProperties.getTimeout())
                .build();
        client.start();
        return client;
    }
}
