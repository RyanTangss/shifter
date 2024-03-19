package top.ryantang.shifter.autoconfigure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ApplicationProperties.class
 *
 * @author RyanTang
 * @date 2024/3/19 14:51
 * Create by Intellij idea!
 */
@Data
@ConfigurationProperties(prefix = "shifter.application")
public class ApplicationProperties {
    private String name;
}
