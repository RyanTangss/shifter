package top.ryantang.shifter.autoconfigure.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ryantang.shifter.autoconfigure.properties.ShifterProperties;
import top.ryantang.shifter.autoconfigure.utils.CuratorFrameworkUtils;

import javax.annotation.Resource;

/**
 * ShifterAutoConfigure.class
 *
 * @author RyanTang
 * @date 2024/3/19 13:51
 * Create by Intellij idea!
 */
@Configuration
//@ConditionalOnClass({, })
@EnableConfigurationProperties({ShifterProperties.class})
public class ShifterAutoConfigure {

    @Resource
    private ShifterProperties shifterProperties;

    //    @Bean(initMethod = "start", destroyMethod = "close")
    @Bean
    public CuratorFramework curatorFramework() throws InterruptedException {
        return CuratorFrameworkUtils.curatorFrameworkBuild(shifterProperties.getZookeeper());
    }

    //    @Bean(initMethod = "start", destroyMethod = "close")
    @Bean
    @ConditionalOnBean(CuratorFramework.class)
    public ServiceDiscovery<Object> serviceDiscovery(CuratorFramework curatorFramework) {
        return CuratorFrameworkUtils.serviceDiscovery(curatorFramework);
    }




}
