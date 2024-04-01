package top.ryantang.shifter.autoconfigure.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import top.ryantang.shifter.autoconfigure.properties.ShifterProperties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.InetAddress;

/**
 * ServiceRegisterConfig.class
 *
 * @author RyanTang
 * @date 2024/3/28 13:45
 * Create by Intellij idea!
 */
@Configuration
public class ServiceRegisterConfig {

    @Resource
    private CuratorFramework curatorFramework;

    @Resource
    private ShifterProperties shifterProperties;

    @Resource
    private ServiceDiscovery<Object> serviceDiscovery;
    @Resource
    private Environment environment;

    @PostConstruct
    public void registerService() throws Exception {
        if (curatorFramework.getState() == CuratorFrameworkState.STARTED) {
            ServiceInstance<Object> serviceInstance = ServiceInstance.builder()
                    .name(shifterProperties.getApplication().getName())
                    .address(InetAddress.getLocalHost().getHostAddress())
                    // 获取服务端口,默认8080
                    .port(environment.getProperty("server.port",Integer.class,8080))
                    .build();
            serviceDiscovery.registerService(serviceInstance);
        }
    }

    @PostConstruct
    public void discoveryService(){
        // TODO 2024-04-01 服务发现逻辑待实现
    }
}
