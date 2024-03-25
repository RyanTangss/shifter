package top.ryantang.shifter.autoconfigure.properties;

import lombok.Data;

/**
 * ZookeeperProperties.class
 *
 * @author RyanTang
 * @date 2024/3/19 14:54
 * Create by Intellij idea!
 */
@Data
public class ZookeeperProperties {

    private String addr;

    private Integer connectionTimeout;

    private Integer sessionTimeout;

}
