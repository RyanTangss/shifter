package top.ryantang.shifter.autoconfigure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * ShifterProperties.class
 *
 * @author RyanTang
 * @date 2024/3/19 14:01
 * Create by Intellij idea!
 */
@Data
@ConfigurationProperties(prefix = "shifter")
public class ShifterProperties {

    @NestedConfigurationProperty
    private ApplicationProperties application = new ApplicationProperties();

    @NestedConfigurationProperty
    private ZookeeperProperties zookeeper = new ZookeeperProperties();
}
