package top.ryantang.shifter.autoconfigure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ZookeeperProperties.class
 *
 * @author RyanTang
 * @date 2024/3/19 14:54
 * Create by Intellij idea!
 */
@Data
@ConfigurationProperties(prefix = "shifter.zookeeper")
public class ZookeeperProperties {

    private String addr;

    private Integer timeout;

}
