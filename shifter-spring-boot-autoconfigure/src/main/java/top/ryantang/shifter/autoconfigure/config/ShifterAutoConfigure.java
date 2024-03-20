package top.ryantang.shifter.autoconfigure.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.ryantang.shifter.autoconfigure.properties.ApplicationProperties;
import top.ryantang.shifter.autoconfigure.properties.ZookeeperProperties;
import top.ryantang.shifter.autoconfigure.properties.ShifterProperties;

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

}
