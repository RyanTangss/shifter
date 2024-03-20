package top.ryantang.shifter.autoconfigure.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.ServiceCacheListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ryantang.shifter.autoconfigure.entity.ServiceInstanceEntity;
import top.ryantang.shifter.autoconfigure.properties.ApplicationProperties;
import top.ryantang.shifter.autoconfigure.properties.ZookeeperProperties;
import top.ryantang.shifter.autoconfigure.properties.ShifterProperties;
import top.ryantang.shifter.autoconfigure.utils.CuratorFrameworkUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.InetAddress;

/**
 * ShifterAutoConfigure.class
 *
 * @author RyanTang
 * @date 2024/3/19 13:51
 * Create by Intellij idea!
 */
@Configuration
//@ConditionalOnClass({, })
@EnableConfigurationProperties({ShifterProperties.class, ApplicationProperties.class, ZookeeperProperties.class})
public class ShifterAutoConfigure {

    private final ShifterProperties shifterProperties;

    @Resource
    private  ServiceDiscovery<Object> serviceDiscovery;

    public ShifterAutoConfigure(ShifterProperties shifterProperties) {
        this.shifterProperties = shifterProperties;
    }


    @Bean(initMethod = "start",destroyMethod = "close")
    public CuratorFramework curatorFramework(){
        return CuratorFrameworkUtils.curatorFrameworkBuild(shifterProperties.getZookeeper());
    }

    @Bean(initMethod = "start",destroyMethod = "close")
    @ConditionalOnBean(CuratorFramework.class)
    public ServiceDiscovery<ServiceInstanceEntity> serviceDiscovery(CuratorFramework curatorFramework){
        return CuratorFrameworkUtils.serviceDiscovery(curatorFramework);
    }



    @PostConstruct
    public void registerService() throws Exception {
        ServiceInstance<Object> serviceInstance = ServiceInstance.builder()
                .name(shifterProperties.getApplication().getName())
                .address(InetAddress.getLocalHost().getHostAddress())
                .port(8080)
                .build();
        serviceDiscovery.registerService(serviceInstance);
    }

    @PostConstruct
    public void discoverService() throws Exception {
        ServiceCacheListener serviceCacheListener = new ServiceCacheListener() {
            @Override
            public void cacheChanged() {

            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

            }
        };
        serviceDiscovery.queryForInstances(shifterProperties.getApplication().getName()).forEach(serviceInstance -> {
            System.out.println("Existing service: " + serviceInstance);
        });
        serviceDiscovery.serviceCacheBuilder()
                .name(shifterProperties.getApplication().getName())
                .build()
                .addListener(serviceCacheListener);
    }



}
