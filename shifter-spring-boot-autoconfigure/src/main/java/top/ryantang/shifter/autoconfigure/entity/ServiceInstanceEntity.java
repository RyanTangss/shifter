package top.ryantang.shifter.autoconfigure.entity;

import lombok.Data;

/**
 * ServiceInstance.class
 *
 * @author RyanTang
 * @date 2024/3/20 22:01
 * Create by intellij idea!
 */
@Data
public class ServiceInstanceEntity {

    private String id;

    private String ip;

    private Integer port;

    private String serviceName;
}
